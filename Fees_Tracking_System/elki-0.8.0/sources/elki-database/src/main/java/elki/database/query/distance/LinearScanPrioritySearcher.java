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
package elki.database.query.distance;

import elki.database.ids.DBIDIter;
import elki.database.ids.DBIDRef;
import elki.database.query.LinearScanQuery;
import elki.database.query.PrioritySearcher;

/**
 * Default linear scan search class.
 * <p>
 * This is a fallback option - results are not returned in order, and (as
 * always) may exceed the given cutoff threshold.
 *
 * @author Erich Schubert
 * @since 0.4.0
 *
 * @has - - - DistanceQuery
 *
 * @param <Q> Query type
 * @param <O> Relation object type
 */
public abstract class LinearScanPrioritySearcher<Q, O> implements PrioritySearcher<Q>, LinearScanQuery {
  /**
   * Distance to use.
   */
  protected DistanceQuery<O> distanceQuery;

  /**
   * Iterator.
   */
  protected DBIDIter iter;

  /**
   * Current query object.
   */
  protected O query;

  /**
   * Current distance
   */
  protected double curdist;

  /**
   * Constructor.
   *
   * @param distanceQuery Distance function to use
   */
  public LinearScanPrioritySearcher(DistanceQuery<O> distanceQuery) {
    super();
    this.distanceQuery = distanceQuery;
  }

  /**
   * Implementation of the search function.
   *
   * @param query Query object
   * @return this
   */
  protected PrioritySearcher<Q> realSearch(O query) {
    this.query = query;
    this.iter = distanceQuery.getRelation().iterDBIDs();
    this.curdist = Double.NaN;
    return this;
  }

  @Override
  public boolean valid() {
    return iter.valid();
  }

  @Override
  public PrioritySearcher<Q> advance() {
    iter.advance();
    curdist = Double.NaN;
    return this;
  }

  @Override
  public int internalGetIndex() {
    return iter.internalGetIndex();
  }

  @Override
  public PrioritySearcher<Q> decreaseCutoff(double threshold) {
    return this; // Ignored
  }

  @Override
  public double computeExactDistance() {
    return curdist == curdist ? curdist : (curdist = distanceQuery.distance(query, iter));
  }

  @Override
  public double getApproximateAccuracy() {
    return curdist == curdist ? 0 : Double.NaN;
  }

  @Override
  public double getApproximateDistance() {
    return curdist; // May be NaN, if not computed yet.
  }

  @Override
  public double getLowerBound() {
    return curdist; // May be NaN, if not computed yet.
  }

  @Override
  public double getUpperBound() {
    return curdist; // May be NaN, if not computed yet.
  }

  @Override
  public double allLowerBound() {
    return iter.valid() ? 0 : Double.POSITIVE_INFINITY;
  }

  /**
   * Search by Object.
   *
   * @author Erich Schubert
   *
   * @param <O> Relation type
   */
  public static class ByObject<O> extends LinearScanPrioritySearcher<O, O> {
    /**
     * Constructor.
     *
     * @param distanceQuery distance query
     */
    public ByObject(DistanceQuery<O> distanceQuery) {
      super(distanceQuery);
    }

    @Override
    public PrioritySearcher<O> search(O query) {
      return realSearch(query);
    }
  }

  /**
   * Search by DBID.
   *
   * @author Erich Schubert
   *
   * @param <O> Relation type
   */
  public static class ByDBID<O> extends LinearScanPrioritySearcher<DBIDRef, O> {
    /**
     * Constructor.
     *
     * @param distanceQuery distance query
     */
    public ByDBID(DistanceQuery<O> distanceQuery) {
      super(distanceQuery);
    }

    @Override
    public PrioritySearcher<DBIDRef> search(DBIDRef query) {
      return realSearch(distanceQuery.getRelation().get(query));
    }
  }
}
