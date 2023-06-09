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
package elki.utilities.exceptions;

/**
 * Exception thrown when a particular code path was not yet implemented.
 *
 * @author Erich Schubert
 * @since 0.6.0
 */
public class NotImplementedException extends AbortException {
  /**
   * Serial version.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   *
   * @param message Error message
   * @param cause Cause
   */
  public NotImplementedException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructor.
   *
   * @param message Error message
   */
  public NotImplementedException(String message) {
    super(message);
  }

  /**
   * "Not implemented yet" exception.
   */
  public NotImplementedException() {
    super("Sorry, not implemented yet. Please contribute to ELKI.");
  }
}
