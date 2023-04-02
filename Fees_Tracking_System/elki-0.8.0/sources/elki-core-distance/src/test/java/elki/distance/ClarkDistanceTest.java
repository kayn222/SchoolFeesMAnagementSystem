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
package elki.distance;

import org.junit.Test;

import elki.math.MathUtil;
import elki.utilities.ELKIBuilder;

/**
 * Unit test for Clark distance.
 *
 * @author Erich Schubert
 * @since 0.7.5
 */
public class ClarkDistanceTest extends AbstractDistanceTest {
  @Test
  public void testSpatialConsistency() {
    // Also test the builder - we could have just used .STATIC
    ClarkDistance dist = new ELKIBuilder<>(ClarkDistance.class).build();
    basicChecks(dist);
    assertVaryingLengthBasic(dist, new double[] { 1, 0, MathUtil.SQRTHALF, MathUtil.SQRTHALF, 1, MathUtil.SQRTHALF }, 0);
    assertSpatialConsistency(dist);
    assertNonnegativeSpatialConsistency(dist);
  }
}