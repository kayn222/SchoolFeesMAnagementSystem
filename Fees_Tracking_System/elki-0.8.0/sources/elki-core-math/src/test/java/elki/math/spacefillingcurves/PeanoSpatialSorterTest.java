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
package elki.math.spacefillingcurves;

import static elki.math.spacefillingcurves.ZCurveSpatialSorterTest.assertSameOrder;
import static elki.math.spacefillingcurves.ZCurveSpatialSorterTest.shuffle;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

import elki.math.spacefillingcurves.ZCurveSpatialSorterTest.V;

/**
 * Class to unit test the Peano curve based spatial sorter.
 * 
 * Note that it is not bit based, but it repeatedly subdivides [min;max].
 * 
 * @author Erich Schubert
 * @since 0.7.5
 */
public class PeanoSpatialSorterTest {
  @Test
  public void testBasic() {
    ArrayList<V> vs = new ArrayList<>();
    vs.add(new V(0, 0, 0));
    vs.add(new V(0, 0, .5));
    vs.add(new V(0, .5, .5));
    vs.add(new V(0, .5, 1));
    vs.add(new V(0, 0, 1));
    vs.add(new V(0, 0, 2));
    vs.add(new V(0, 1, 2));
    vs.add(new V(0, 1, 1));
    vs.add(new V(0, 1, 0));
    vs.add(new V(0, 2, 0));
    vs.add(new V(1, 0, 0));
    vs.add(new V(2, 0, 0));
    ArrayList<V> x = shuffle(vs, new Random(0L));
    PeanoSpatialSorter.STATIC.sort(x);
    assertSameOrder("Peano order incorrect", vs, x);
  }
}
