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
 * Unit test for the Weibull distribution in ELKI.
 * <p>
 * The reference values were computed using GNU R and SciPy.
 * 
 * @author Erich Schubert
 * @since 0.7.0
 */
public class WeibullDistributionTest extends AbstractDistributionTest {
  @Test
  public void testPDF() {
    load("weibull.ascii.gz");
    assertPDF(new WeibullDistribution(1., 1.), "pdf_scipy_1_1", 1e-15);
    assertPDF(new WeibullDistribution(2., 1.), "pdf_scipy_2_1", 1e-14);
    assertPDF(new WeibullDistribution(4., 1.), "pdf_scipy_4_1", 1e-14);
    assertPDF(new WeibullDistribution(4., 10.), "pdf_scipy_4_10", 1e-14);
    assertPDF(new WeibullDistribution(.1, 1.), "pdf_scipy_01_1", 1e-15);
    assertPDF(new WeibullDistribution(.1, 4.), "pdf_scipy_01_4", 1e-15);
    assertPDF(new WeibullDistribution(.1, 10.), "pdf_scipy_01_10", 1e-14);
    assertPDF(new WeibullDistribution(.1, 20.), "pdf_scipy_01_20", 1e-15);

    assertPDF(new WeibullDistribution(1., 1.), "pdf_gnur_1_1", 1e-15);
    assertPDF(new WeibullDistribution(2., 1.), "pdf_gnur_2_1", 1e-15);
    assertPDF(new WeibullDistribution(4., 1.), "pdf_gnur_4_1", 1e-15);
    assertPDF(new WeibullDistribution(4., 10.), "pdf_gnur_4_10", 1e-15);
    assertPDF(new WeibullDistribution(.1, 1.), "pdf_gnur_01_1", 1e-15);
    assertPDF(new WeibullDistribution(.1, 4.), "pdf_gnur_01_4", 1e-15);
    assertPDF(new WeibullDistribution(.1, 10.), "pdf_gnur_01_10", 1e-14);
    assertPDF(new WeibullDistribution(.1, 20.), "pdf_gnur_01_20", 1e-14);
  }

  @Test
  public void testLogPDF() {
    load("weibull.ascii.gz");
    assertLogPDF(new WeibullDistribution(1., 1.), "logpdf_scipy_1_1", 1e-15);
    assertLogPDF(new WeibullDistribution(2., 1.), "logpdf_scipy_2_1", 1e-14);
    assertLogPDF(new WeibullDistribution(4., 1.), "logpdf_scipy_4_1", 1e-14);
    assertLogPDF(new WeibullDistribution(4., 10.), "logpdf_scipy_4_10", 1e-14);
    assertLogPDF(new WeibullDistribution(.1, 1.), "logpdf_scipy_01_1", 1e-15);
    assertLogPDF(new WeibullDistribution(.1, 4.), "logpdf_scipy_01_4", 1e-14);
    assertLogPDF(new WeibullDistribution(.1, 10.), "logpdf_scipy_01_10", 1e-14);
    assertLogPDF(new WeibullDistribution(.1, 20.), "logpdf_scipy_01_20", 1e-15);

    assertLogPDF(new WeibullDistribution(1., 1.), "logpdf_gnur_1_1", 1e-15);
    assertLogPDF(new WeibullDistribution(2., 1.), "logpdf_gnur_2_1", 1e-15);
    assertLogPDF(new WeibullDistribution(4., 1.), "logpdf_gnur_4_1", 1e-15);
    assertLogPDF(new WeibullDistribution(4., 10.), "logpdf_gnur_4_10", 1e-15);
    assertLogPDF(new WeibullDistribution(.1, 1.), "logpdf_gnur_01_1", 1e-15);
    assertLogPDF(new WeibullDistribution(.1, 4.), "logpdf_gnur_01_4", 1e-15);
    assertLogPDF(new WeibullDistribution(.1, 10.), "logpdf_gnur_01_10", 1e-15);
    assertLogPDF(new WeibullDistribution(.1, 20.), "logpdf_gnur_01_20", 1e-15);
  }

  @Test
  public void testCDF() {
    load("weibull.ascii.gz");
    assertCDF(new WeibullDistribution(1., 1.), "cdf_scipy_1_1", 1e-12);
    assertCDF(new WeibullDistribution(2., 1.), "cdf_scipy_2_1", 1e-12);
    assertCDF(new WeibullDistribution(4., 1.), "cdf_scipy_4_1", 1e-11);
    assertCDF(new WeibullDistribution(4., 10.), "cdf_scipy_4_10", 1e-11);
    assertCDF(new WeibullDistribution(.1, 1.), "cdf_scipy_01_1", 1e-15);
    assertCDF(new WeibullDistribution(.1, 4.), "cdf_scipy_01_4", 1e-15);
    assertCDF(new WeibullDistribution(.1, 10.), "cdf_scipy_01_10", 1e-15);
    assertCDF(new WeibullDistribution(.1, 20.), "cdf_scipy_01_20", 1e-15);

    assertCDF(new WeibullDistribution(1., 1.), "cdf_gnur_1_1", 1e-14);
    assertCDF(new WeibullDistribution(2., 1.), "cdf_gnur_2_1", 1e-14);
    assertCDF(new WeibullDistribution(4., 1.), "cdf_gnur_4_1", 1e-13);
    assertCDF(new WeibullDistribution(4., 10.), "cdf_gnur_4_10", 1e-13);
    assertCDF(new WeibullDistribution(.1, 1.), "cdf_gnur_01_1", 1e-15);
    assertCDF(new WeibullDistribution(.1, 4.), "cdf_gnur_01_4", 1e-15);
    assertCDF(new WeibullDistribution(.1, 10.), "cdf_gnur_01_10", 1e-15);
    assertCDF(new WeibullDistribution(.1, 20.), "cdf_gnur_01_20", 1e-15);
  }

  @Test
  public void testQuantile() {
    load("weibull.ascii.gz");
    assertQuantile(new WeibullDistribution(1., 1.), "quant_scipy_1_1", 1e-15);
    assertQuantile(new WeibullDistribution(2., 1.), "quant_scipy_2_1", 1e-15);
    assertQuantile(new WeibullDistribution(4., 1.), "quant_scipy_4_1", 1e-13);
    assertQuantile(new WeibullDistribution(4., 10.), "quant_scipy_4_10", 1e-13);
    assertQuantile(new WeibullDistribution(.1, 1.), "quant_scipy_01_1", 1e-14);
    assertQuantile(new WeibullDistribution(.1, 4.), "quant_scipy_01_4", 1e-13);
    assertQuantile(new WeibullDistribution(.1, 10.), "quant_scipy_01_10", 1e-13);
    assertQuantile(new WeibullDistribution(.1, 20.), "quant_scipy_01_20", 1e-13);

    assertQuantile(new WeibullDistribution(1., 1.), "quant_gnur_1_1", 1e-15);
    assertQuantile(new WeibullDistribution(2., 1.), "quant_gnur_2_1", 1e-15);
    assertQuantile(new WeibullDistribution(4., 1.), "quant_gnur_4_1", 1e-13);
    assertQuantile(new WeibullDistribution(4., 10.), "quant_gnur_4_10", 1e-13);
    assertQuantile(new WeibullDistribution(.1, 1.), "quant_gnur_01_1", 1e-13);
    assertQuantile(new WeibullDistribution(.1, 4.), "quant_gnur_01_4", 1e-13);
    assertQuantile(new WeibullDistribution(.1, 10.), "quant_gnur_01_10", 1e-13);
    assertQuantile(new WeibullDistribution(.1, 20.), "quant_gnur_01_20", 1e-13);
  }

  @Test
  public void testParameterizer() throws ClassInstantiationException {
    load("weibull.ascii.gz");
    Distribution dist = new ELKIBuilder<>(WeibullDistribution.class) //
        .with(WeibullDistribution.Par.SHAPE_ID, .1) //
        .with(WeibullDistribution.Par.SCALE_ID, 4.).build();
    assertPDF(dist, "pdf_scipy_01_4", 1e-15);
  }

  @Test
  public void testRandom() {
    assertRandom(new WeibullDistribution(0.1, 0.9, 1), new Random(0L), 10000, 1e-2);
    assertRandom(new WeibullDistribution(1.41, 3.14, 2), new Random(0L), 10000, 1e-2);
    assertRandom(new WeibullDistribution(3.14, 1.41, 3), new Random(0L), 10000, 1e-2);
  }
}
