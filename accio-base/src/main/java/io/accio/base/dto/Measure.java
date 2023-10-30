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

package io.accio.base.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Measure
{
    public static Measure measure(String name, String type, String operator, String refColumn)
    {
        return new Measure(name, type, operator, refColumn);
    }

    private final String name;
    private final String type;
    private final String operator;
    private final String refColumn;

    @JsonCreator
    public Measure(
            @JsonProperty("name") String name,
            @JsonProperty("type") String type,
            @JsonProperty("operator") String operator,
            @JsonProperty("refColumn") String refColumn)
    {
        this.name = name;
        this.type = type;
        this.operator = operator;
        this.refColumn = refColumn;
    }

    @JsonProperty
    public String getName()
    {
        return name;
    }

    @JsonProperty
    public String getType()
    {
        return type;
    }

    @JsonProperty
    public String getOperator()
    {
        return operator;
    }

    @JsonProperty
    public String getRefColumn()
    {
        return refColumn;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Measure measure = (Measure) o;
        return Objects.equals(name, measure.name) &&
                Objects.equals(type, measure.type) &&
                Objects.equals(operator, measure.operator) &&
                Objects.equals(refColumn, measure.refColumn);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, type, operator, refColumn);
    }
}
