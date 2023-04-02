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
package elki.math.statistics.distribution;

import java.util.Random;

import org.junit.Test;

import elki.utilities.ELKIBuilder;
import elki.utilities.exceptions.ClassInstantiationException;

/**
 * Unit test for the ExpGamma distribution in ELKI.
 * <p>
 * The reference values were computed using SciPy
 * <p>
 * FIXME: some values do not make sense.
 * 
 * @author Erich Schubert
 * @since 0.7.5
 */
public class ExpGammaDistributionTest extends AbstractDistributionTest {
  @Test
  public void testPDF() {
    load("expgamma.ascii.gz");
    assertPDF(new ExpGammaDistribution(1., 1., 0.), "pdf_scipy_1_1", 1e-15);
    assertPDF(new ExpGammaDistribution(2., 1., 0.), "pdf_scipy_2_1", 1e-15);
    assertPDF(new ExpGammaDistribution(4., 1., 0.), "pdf_scipy_4_1", 1e-14);
    assertPDF(new ExpGammaDistribution(4., 10, 0.), "pdf_scipy_4_10", 1e-15);
    assertPDF(new ExpGammaDistribution(.1, 10, 0.), "pdf_scipy_01_10", 1e-15);
    assertPDF(new ExpGammaDistribution(.1, 20, 0.), "pdf_scipy_01_20", 1e-15);
    assertPDF(new ExpGammaDistribution(.1, 4., 0.), "pdf_scipy_01_4", 1e-15);
    assertPDF(new ExpGammaDistribution(.1, 1., 0.), "pdf_scipy_01_1", 1e-15);
  }

  @Test
  public void testLogPDF() {
    load("expgamma.ascii.gz");
    assertLogPDF(new ExpGammaDistribution(1., 1., 0.), "logpdf_scipy_1_1", 1e-15);
    assertLogPDF(new ExpGammaDistribution(2., 1., 0.), "logpdf_scipy_2_1", 1e-15);
    assertLogPDF(new ExpGammaDistribution(4., 1., 0.), "logpdf_scipy_4_1", 1e-15);
    // For the following, SciPy appears to lose numerical precision and returns
    // inf values:
    // checkLogPDF(new LogGammaAlternateDistribution(4., 10, 0.),
    // "logpdf_scipy_4_10", 1e-14);
    // checkLogPDF(new LogGammaAlternateDistribution(.1, 10, 0.),
    // "logpdf_scipy_01_10", 1e-15);
    // checkLogPDF(new LogGammaAlternateDistribution(.1, 20, 0.),
    // "logpdf_scipy_01_20", 1e-14);
    // checkLogPDF(new LogGammaAlternateDistribution(.1, 4., 0.),
    // "logpdf_scipy_01_4", 1e-15);
    assertLogPDF(new ExpGammaDistribution(.1, 1., 0.), "logpdf_scipy_01_1", 1e-15);
  }

  @Test
  public void testCDF() {
    load("expgamma.ascii.gz");
    assertCDF(new ExpGammaDistribution(1., 1., 0.), "cdf_scipy_1_1", 1e-15);
    assertCDF(new ExpGammaDistribution(2., 1., 0.), "cdf_scipy_2_1", 1e-15);
    assertCDF(new ExpGammaDistribution(4., 1., 0.), "cdf_scipy_4_1", 1e-15);
    assertCDF(new ExpGammaDistribution(4., 10, 0.), "cdf_scipy_4_10", 1e-15);
    assertCDF(new ExpGammaDistribution(.1, 10, 0.), "cdf_scipy_01_10", 1e-15);
    assertCDF(new ExpGammaDistribution(.1, 20, 0.), "cdf_scipy_01_20", 1e-15);
    assertCDF(new ExpGammaDistribution(.1, 4., 0.), "cdf_scipy_01_4", 1e-15);
    assertCDF(new ExpGammaDistribution(.1, 1., 0.), "cdf_scipy_01_1", 1e-15);
  }

  @Test
  public void testQuantile() {
    load("expgamma.ascii.gz");
    assertQuantile(new ExpGammaDistribution(1., 1., 0.), "quant_scipy_1_1", 1e-14);
    assertQuantile(new ExpGammaDistribution(2., 1., 0.), "quant_scipy_2_1", 1e-13);
    assertQuantile(new ExpGammaDistribution(4., 1., 0.), "quant_scipy_4_1", 1e-14);
    // Here, sklearn appears to have numeric problems (inf) again
    assertQuantile(new ExpGammaDistribution(4., 10, 0.), "quant_scipy_4_10", 1e0);
    // checkQuantile(new ExpGammaDistribution(.1, 10, 0.), "quant_scipy_01_10",
    // 1e-14);
    // checkQuantile(new ExpGammaDistribution(.1, 20, 0.), "quant_scipy_01_20",
    // 1e-14);
    // checkQuantile(new ExpGammaDistribution(.1, 4., 0.), "quant_scipy_01_4",
    // 1e-14);
    // checkQuantile(new ExpGammaDistribution(.1, 1., 0.), "quant_scipy_01_1",
    // 1e-14);
  }

  @Test
  public void testParameterizer() throws ClassInstantiationException {
    load("expgamma.ascii.gz");
    Distribution dist = new ELKIBuilder<>(ExpGammaDistribution.class) //
        .with(ExpGammaDistribution.Par.K_ID, 2.) //
        .with(ExpGammaDistribution.Par.THETA_ID, 1.) //
        .with(ExpGammaDistribution.Par.SHIFT_ID, 0.).build();
    assertPDF(dist, "pdf_scipy_2_1", 1e-15);
  }

  @Test
  public void testRandom() {
    assertRandom(new ExpGammaDistribution(1, 1, 0), new Random(0L), 10000, 1e-2);
    assertRandom(new ExpGammaDistribution(0.1, 0.9, 1), new Random(0L), 10000, 1e-2);
    assertRandom(new ExpGammaDistribution(1.41, 3.14, 2), new Random(0L), 10000, 1e-2);
    assertRandom(new ExpGammaDistribution(3.14, 1.41, 3), new Random(0L), 10000, 1e-2);
  }
}
