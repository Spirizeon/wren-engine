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

package io.graphmdl.sqlrewrite;

import io.graphmdl.base.GraphMDL;
import io.graphmdl.base.SessionContext;
import io.graphmdl.sqlrewrite.analyzer.Analysis;
import io.trino.sql.tree.Statement;

public interface GraphMDLRule
{
    Statement apply(Statement root, SessionContext sessionContext, GraphMDL graphMDL);

    Statement apply(Statement root, SessionContext sessionContext, Analysis analysis, GraphMDL graphMDL);
}