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
package io.trino.execution.sql;

import io.cml.spi.CmlException;
import io.trino.sql.SqlFormatter;
import io.trino.sql.parser.ParsingException;
import io.trino.sql.parser.ParsingOptions;
import io.trino.sql.parser.SqlParser;
import io.trino.sql.tree.Statement;

import javax.annotation.Nullable;

import static io.cml.spi.metadata.StandardErrorCode.GENERIC_INTERNAL_ERROR;
import static io.trino.sql.parser.ParsingOptions.DecimalLiteralTreatment.REJECT;
import static java.lang.String.format;

public final class SqlFormatterUtil
{
    private SqlFormatterUtil() {}

    public static String getFormattedSql(Statement statement, SqlParser sqlParser)
    {
        String sql = SqlFormatter.formatSql(statement);

        // verify round-trip
        Statement parsed;
        try {
            ParsingOptions parsingOptions = new ParsingOptions(REJECT /* formatted SQL should be unambiguous */);
            parsed = sqlParser.createStatement(sql, parsingOptions);
        }
        catch (ParsingException e) {
            throw formattingFailure(e, "Formatted query does not parse", statement, sql);
        }
        if (!statement.equals(parsed)) {
            throw formattingFailure(null, "Query does not round-trip", statement, sql);
        }

        return sql;
    }

    private static CmlException formattingFailure(@Nullable Throwable cause, String message, Statement statement, String sql)
    {
        CmlException exception = new CmlException(GENERIC_INTERNAL_ERROR, message, cause);
        exception.addSuppressed(new RuntimeException("Statement: " + statement));
        exception.addSuppressed(new RuntimeException(format("Formatted: [%s]", sql)));
        return exception;
    }
}
