/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.trino.sql.tree;

public final class SetViewAuthorization
        extends SetAuthorizationStatement
{
    public SetViewAuthorization(QualifiedName source, PrincipalSpecification principal)
    {
        super(source, principal);
    }

    public SetViewAuthorization(NodeLocation location, QualifiedName source, PrincipalSpecification principal)
    {
        super(location, source, principal);
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context)
    {
        return visitor.visitSetViewAuthorization(this, context);
    }
}
