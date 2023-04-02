/*
 * This file is part of ELKI:
 * Environment for Developing KDD-Applications Supported by Index-Structures
 *
 * Copyright (C) 2022
 * ELKI Development Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package tutorial.outlier;

import elki.Algorithm;
import elki.data.type.TypeInformation;
import elki.data.type.TypeUtil;
import elki.database.datastore.DataStoreFactory;
import elki.database.datastore.DataStoreUtil;
import elki.database.datastore.WritableDoubleDataStore;
import elki.database.ids.*;
import elki.database.query.QueryBuilder;
import elki.database.query.knn.KNNSearcher;
import elki.database.relation.DoubleRelation;
import elki.database.relation.MaterializedDoubleRelation;
import elki.database.relation.Relation;
import elki.distance.Distance;
import elki.distance.minkowski.EuclideanDistance;
import elki.math.DoubleMinMax;
import elki.math.MeanVariance;
import elki.outlier.OutlierAlgorithm;
import elki.result.outlier.BasicOutlierScoreMeta;
import elki.result.outlier.OutlierResult;
import elki.result.outlier.OutlierScoreMeta;
import elki.utilities.optionhandling.OptionID;
import elki.utilities.optionhandling.Parameterizer;
import elki.utilities.optionhandling.constraints.CommonConstraints;
import elki.utilities.optionhandling.parameterization.Parameterization;
import elki.utilities.optionhandling.parameters.IntParameter;
import elki.utilities.optionhandling.parameters.ObjectParameter;

/**
 * A simple outlier detection algorithm that computes the standard deviation of
 * the kNN distances.
 *
 * @author Erich Schubert
 * @since 0.5.0
 *
 * @param <O> Object type
 */
public class DistanceStddevOutlier<O> implements OutlierAlgorithm {
  /**
   * Distance function used.
   */
  protected Distance<? super O> distance;

  /**
   * Number of neighbors to get.
   */
  protected int k;

  /**
   * Constructor.
   *
   * @param distance Distance function to use
   * @param k Number of neighbors to use
   */
  public DistanceStddevOutlier(Distance<? super O> distance, int k) {
    super();
    this.distance = distance;
    this.k = k;
  }

  @Override
  public TypeInformation[] getInputTypeRestriction() {
    return TypeUtil.array(distance.getInputTypeRestriction());
  }

  /**
   * Run the outlier detection algorithm
   *
   * @param relation Relation to analyze
   * @return Outlier score result
   */
  public OutlierResult run(Relation<O> relation) {
    // Get a nearest neighbor query on the relation.
    KNNSearcher<DBIDRef> knnq = new QueryBuilder<>(relation, distance).kNNByDBID(k);
    // Output data storage
    WritableDoubleDataStore scores = DataStoreUtil.makeDoubleStorage(relation.getDBIDs(), DataStoreFactory.HINT_DB);
    // Track minimum and maximum scores
    DoubleMinMax minmax = new DoubleMinMax();

    // Iterate over all objects
    for(DBIDIter iter = relation.iterDBIDs(); iter.valid(); iter.advance()) {
      KNNList neighbors = knnq.getKNN(iter, k);
      // Aggregate distances
      MeanVariance mv = new MeanVariance();
      for(DoubleDBIDListIter neighbor = neighbors.iter(); neighbor.valid(); neighbor.advance()) {
        // Skip the object itself. The 0 is not very informative.
        if(DBIDUtil.equal(iter, neighbor)) {
          continue;
        }
        mv.put(neighbor.doubleValue());
      }
      // Store score
      scores.putDouble(iter, mv.getSampleStddev());
    }

    // Wrap the result in the standard containers
    // Actual min-max, theoretical min-max!
    OutlierScoreMeta meta = new BasicOutlierScoreMeta(minmax.getMin(), minmax.getMax(), 0, Double.POSITIVE_INFINITY);
    DoubleRelation rel = new MaterializedDoubleRelation("stddev-outlier", relation.getDBIDs(), scores);
    return new OutlierResult(meta, rel);
  }

  /**
   * Parameterization class
   *
   * @author Erich Schubert
   *
   * @hidden
   *
   * @param <O> Object type
   */
  public static class Par<O> implements Parameterizer {
    /**
     * Option ID for parameterization.
     */
    public static final OptionID K_ID = new OptionID("stddevout.k", "Number of neighbors to get for stddev based outlier detection.");

    /**
     * The distance function to use.
     */
    protected Distance<? super O> distance;

    /**
     * Number of neighbors to use.
     */
    protected int k;

    @Override
    public void configure(Parameterization config) {
      new ObjectParameter<Distance<? super O>>(Algorithm.Utils.DISTANCE_FUNCTION_ID, Distance.class, EuclideanDistance.class) //
          .grab(config, x -> distance = x);
      new IntParameter(K_ID) //
          .addConstraint(CommonConstraints.GREATER_THAN_ONE_INT) //
          .grab(config, x -> k = x);
    }

    @Override
    public DistanceStddevOutlier<O> make() {
      return new DistanceStddevOutlier<>(distance, k);
    }
  }
}
