/**
 * WS-Attacker - A Modular Web Services Penetration Testing Framework
 * Copyright (C) 2011 Christian Mainka
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package wsattacker.plugin.signatureWrapping.xpath.parts.predicate.factory;

import wsattacker.plugin.signatureWrapping.util.exception.InvalidTypeException;
import wsattacker.plugin.signatureWrapping.xpath.parts.predicate.AndExpression;
import wsattacker.plugin.signatureWrapping.xpath.parts.predicate.concrete.AttributeAndExpression;
import wsattacker.plugin.signatureWrapping.xpath.parts.predicate.concrete.LocalNameAndExpression;
import wsattacker.plugin.signatureWrapping.xpath.parts.predicate.concrete.NamespaceUriAndExpression;
import wsattacker.plugin.signatureWrapping.xpath.parts.predicate.concrete.PositionAndExpression;

public class AndExpressionFactory implements AndExpressionFactoryInterface
{

  @Override
  public AndExpression createAndExpression(String expression)
  {
      try
      {
        return new AttributeAndExpression(expression);
      }
      catch (InvalidTypeException e)
      {
        // Nothing to do, just ignore
      }
      
      
      try
      {
        return new PositionAndExpression(expression);
      }
      catch (InvalidTypeException e)
      {
        // Nothing to do, just ignore
      }
      
      
      try
      {
        return new LocalNameAndExpression(expression);
      }
      catch (InvalidTypeException e)
      {
        // Nothing to do, just ignore
      }
      
      
      try
      {
        return new NamespaceUriAndExpression(expression);
      }
      catch (InvalidTypeException e)
      {
        // Nothing to do, just ignore
      }
      
      
    // No special AndExpression found
    // return generic one
    return new AndExpression(expression);
  }

}
