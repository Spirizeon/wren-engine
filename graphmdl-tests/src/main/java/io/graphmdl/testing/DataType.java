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

package io.graphmdl.testing;

import io.graphmdl.base.type.BigIntType;
import io.graphmdl.base.type.BooleanType;
import io.graphmdl.base.type.ByteaType;
import io.graphmdl.base.type.CharType;
import io.graphmdl.base.type.DoubleType;
import io.graphmdl.base.type.IntegerType;
import io.graphmdl.base.type.JsonType;
import io.graphmdl.base.type.NumericType;
import io.graphmdl.base.type.PGType;
import io.graphmdl.base.type.RealType;
import io.graphmdl.base.type.SmallIntType;
import io.graphmdl.base.type.TinyIntType;
import io.graphmdl.base.type.VarcharType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Optional;
import java.util.function.Function;

import static io.graphmdl.base.type.DateType.DATE;
import static io.graphmdl.base.type.TimestampType.TIMESTAMP;
import static java.lang.String.format;
import static java.time.temporal.ChronoField.NANO_OF_SECOND;

public class DataType<T>
{
    private final String insertType;
    private final PGType<?> pgResultType;
    private final Function<T, String> toLiteral;
    private final Function<T, String> toGraphMDLLiteral;

    public static DataType<Boolean> booleanDataType()
    {
        return dataType("boolean", BooleanType.BOOLEAN);
    }

    public static DataType<Long> bigintDataType()
    {
        return dataType("bigint", BigIntType.BIGINT);
    }

    public static DataType<Integer> integerDataType()
    {
        return dataType("integer", IntegerType.INTEGER);
    }

    public static DataType<Short> smallintDataType()
    {
        return dataType("smallint", SmallIntType.SMALLINT);
    }

    public static DataType<Byte> tinyintDataType()
    {
        return dataType("tinyint", TinyIntType.TINYINT);
    }

    public static DataType<Float> realDataType()
    {
        return dataType("real", RealType.REAL,
                value -> {
                    if (Float.isFinite(value)) {
                        return value.toString();
                    }
                    if (Float.isNaN(value)) {
                        return "nan()";
                    }
                    return format("%sinfinity()", value > 0 ? "+" : "-");
                });
    }

    public static DataType<Double> doubleDataType()
    {
        return dataType("double", DoubleType.DOUBLE,
                value -> {
                    if (Double.isFinite(value)) {
                        return value.toString();
                    }
                    if (Double.isNaN(value)) {
                        return "nan()";
                    }
                    return format("%sinfinity()", value > 0 ? "+" : "-");
                });
    }

    public static DataType<String> byteaDataType()
    {
        return dataType(
                "bytea",
                ByteaType.BYTEA,
                DataType::formatStringLiteral);
    }

    public static DataType<String> jsonDataType()
    {
        return dataType(
                "json",
                JsonType.JSON,
                value -> "JSON " + formatStringLiteral(value));
    }

    public static DataType<String> charDataType()
    {
        return dataType("char", CharType.CHAR, DataType::formatStringLiteral);
    }

    public static DataType<String> charDataType(int size)
    {
        return dataType(format("char(%s)", size), CharType.CHAR, DataType::formatStringLiteral);
    }

    public static DataType<String> varcharDataType(int size)
    {
        return varcharDataType(size, "");
    }

    public static DataType<String> varcharDataType(int size, String properties)
    {
        return varcharDataType(Optional.of(size), properties);
    }

    public static DataType<String> varcharDataType()
    {
        return varcharDataType(Optional.empty(), "");
    }

    private static DataType<String> varcharDataType(Optional<Integer> length, String properties)
    {
        String prefix = length.map(size -> "varchar(" + size + ")").orElse("varchar");
        String suffix = properties.isEmpty() ? "" : " " + properties;
        return stringDataType(prefix + suffix, VarcharType.VARCHAR);
    }

    public static DataType<String> nameDataType()
    {
        return stringDataType("name", VarcharType.VARCHAR);
    }

    public static DataType<String> textDataType()
    {
        return stringDataType("text", VarcharType.VARCHAR);
    }

    public static DataType<String> stringDataType(String insertType, PGType<?> trinoResultType)
    {
        return dataType(insertType, trinoResultType, DataType::formatStringLiteral);
    }

    public static DataType<BigDecimal> decimalDataType()
    {
        return dataType(
                "decimal",
                NumericType.NUMERIC);
    }

    public static DataType<BigDecimal> decimalDataType(int precision, int scale)
    {
        String databaseType = format("decimal(%s, %s)", precision, scale);
        return dataType(
                databaseType,
                NumericType.NUMERIC);
    }

    public static DataType<LocalDate> dateDataType()
    {
        return dataType(
                "date",
                DATE,
                DateTimeFormatter.ofPattern("'DATE '''uuuu-MM-dd''")::format);
    }

    public static DataType<LocalDateTime> timestampDataType(int precision)
    {
        DateTimeFormatterBuilder format = new DateTimeFormatterBuilder()
                .appendPattern("'TIMESTAMP '''")
                .appendPattern("uuuu-MM-dd HH:mm:ss");
        if (precision != 0) {
            format.appendFraction(NANO_OF_SECOND, precision, precision, true);
        }
        format.appendPattern("''");

        return dataType(
                format("timestamp(%s)", precision),
                TIMESTAMP,
                format.toFormatter()::format);
    }

    public static String formatStringLiteral(String value)
    {
        return "'" + value.replace("'", "''") + "'";
    }

    private static <T> DataType<T> dataType(String insertType, PGType<?> graphMDLResultType)
    {
        return new DataType<>(insertType, graphMDLResultType, Object::toString, Object::toString);
    }

    public static <T> DataType<T> dataType(String insertType, PGType<?> graphMDLResultType, Function<T, String> toLiteral)
    {
        return new DataType<>(insertType, graphMDLResultType, toLiteral, toLiteral);
    }

    private DataType(String insertType, PGType<?> pgResultType, Function<T, String> toLiteral, Function<T, String> toGraphMDLLiteral)
    {
        this.insertType = insertType;
        this.pgResultType = pgResultType;
        this.toLiteral = toLiteral;
        this.toGraphMDLLiteral = toGraphMDLLiteral;
    }

    public String toLiteral(T inputValue)
    {
        if (inputValue == null) {
            return "NULL";
        }
        return toLiteral.apply(inputValue);
    }

    public String toGraphMDLLiteral(T inputValue)
    {
        if (inputValue == null) {
            return "NULL";
        }
        return toGraphMDLLiteral.apply(inputValue);
    }

    public String getInsertType()
    {
        return insertType;
    }

    public PGType<?> getPgResultType()
    {
        return pgResultType;
    }
}