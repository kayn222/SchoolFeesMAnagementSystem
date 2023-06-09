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
package elki.result.outlier;

/**
 * Score for outlier values generated by a quotient.
 * 
 * @author Erich Schubert
 * @since 0.3
 *
 */
public class QuotientOutlierScoreMeta extends BasicOutlierScoreMeta {
  /**
   * Constructor with actual minimum and maximum values.
   * 
   * If possible, please also specify a baseline!
   * 
   * @param actualMinimum Actual minimum
   * @param actualMaximum Actual maximum
   */
  public QuotientOutlierScoreMeta(double actualMinimum, double actualMaximum) {
    super(actualMinimum, actualMaximum);
  }

  /**
   * Constructor with all range values.
   * 
   * If possible, please also specify a baseline!
   * 
   * @param actualMinimum Actual minimum
   * @param actualMaximum Actual maximum
   * @param theoreticalMinimum Theoretical minimum
   * @param theoreticalMaximum Theoretical maximum
   */
  public QuotientOutlierScoreMeta(double actualMinimum, double actualMaximum, double theoreticalMinimum, double theoreticalMaximum) {
    super(actualMinimum, actualMaximum, theoreticalMinimum, theoreticalMaximum);
  }

  /**
   * Full constructor with all values.
   * 
   * @param actualMinimum Actual minimum.
   * @param actualMaximum Actual maximum.
   * @param theoreticalMinimum Theoretical minimum.
   * @param theoreticalMaximum Theoretical maximum.
   * @param theoreticalBaseline Theoretical baseline.
   */
  public QuotientOutlierScoreMeta(double actualMinimum, double actualMaximum, double theoreticalMinimum, double theoreticalMaximum, double theoreticalBaseline) {
    super(actualMinimum, actualMaximum, theoreticalMinimum, theoreticalMaximum, theoreticalBaseline);
  }
}
