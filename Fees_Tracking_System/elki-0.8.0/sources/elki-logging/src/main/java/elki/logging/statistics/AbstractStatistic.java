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
package elki.logging.statistics;

/**
 * Abstract base class for statistics tracking.
 * 
 * @author Erich Schubert
 * @since 0.6.0
 */
public abstract class AbstractStatistic implements Statistic {
  /**
   * Key to report the statistic with.
   */
  String key;

  /**
   * Constructor.
   * 
   * @param key Tracking key
   */
  public AbstractStatistic(String key) {
    super();
    this.key = key;
  }

  @Override
  public String getKey() {
    return key;
  }

  @Override
  public String toString() {
    return key + ": " + formatValue();
  }
}
