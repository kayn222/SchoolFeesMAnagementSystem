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
package elki.index.tree.metrical.mtreevariants.mtree;

import org.junit.Test;

import elki.data.DoubleVector;
import elki.index.AbstractIndexStructureTest;
import elki.index.tree.metrical.mtreevariants.query.MTreeKNNByDBID;
import elki.index.tree.metrical.mtreevariants.query.MTreeKNNByObject;
import elki.index.tree.metrical.mtreevariants.query.MTreeRangeByDBID;
import elki.index.tree.metrical.mtreevariants.query.MTreeRangeByObject;
import elki.persistent.AbstractPageFileFactory;
import elki.utilities.ELKIBuilder;

/**
 * Unit test for the M-tree.
 * 
 * @author Erich Schubert
 * @since 0.7.0
 */
public class MTreeTest extends AbstractIndexStructureTest {
  /**
   * Test {@link MTree} using a file based database connection.
   */
  @Test
  public void testEuclidean() {
    MTreeFactory<DoubleVector> factory = new ELKIBuilder<>(MTreeFactory.class) //
        .with(AbstractPageFileFactory.Par.PAGE_SIZE_ID, 300).build();
    assertExactEuclidean(factory, MTreeKNNByObject.class, MTreeRangeByObject.class);
    assertSinglePoint(factory, MTreeKNNByDBID.class, MTreeRangeByDBID.class);
  }
}
