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
package io.cml.spi.metadata;

import io.cml.spi.type.PGType;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static java.util.Collections.emptyMap;
import static java.util.Objects.requireNonNull;

public class ColumnMetadata
{
    private final String name;
    private final PGType<?> type;
    private final boolean nullable;
    private final String comment;
    private final String value;
    private final String extraInfo;
    private final boolean hidden;
    private final Map<String, Object> properties;

    public ColumnMetadata(String name, PGType<?> type, boolean nullable, String comment, String value, String extraInfo, boolean hidden, Map<String, Object> properties)
    {
        this.name = name;
        this.type = type;
        this.nullable = nullable;
        this.comment = comment;
        this.value = value;
        this.extraInfo = extraInfo;
        this.hidden = hidden;
        this.properties = properties;
    }

    public String getName()
    {
        return name;
    }

    public PGType<?> getType()
    {
        return type;
    }

    public boolean isNullable()
    {
        return nullable;
    }

    public Optional<String> getComment()
    {
        return Optional.ofNullable(comment);
    }

    public String getValue()
    {
        return value;
    }

    public Optional<String> getExtraInfo()
    {
        return Optional.ofNullable(extraInfo);
    }

    public boolean isHidden()
    {
        return hidden;
    }

    public Map<String, Object> getProperties()
    {
        return properties;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("ColumnMetadata{");
        sb.append("name='").append(name).append('\'');
        sb.append(", type=").append(type);
        sb.append(", ").append(nullable ? "nullable" : "nonnull");
        if (comment != null) {
            sb.append(", comment='").append(comment).append('\'');
        }
        if (extraInfo != null) {
            sb.append(", extraInfo='").append(extraInfo).append('\'');
        }
        if (hidden) {
            sb.append(", hidden");
        }
        if (!properties.isEmpty()) {
            sb.append(", properties=").append(properties);
        }
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, type, nullable, comment, extraInfo, hidden);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ColumnMetadata other = (ColumnMetadata) obj;
        return Objects.equals(this.name, other.name) &&
                Objects.equals(this.type, other.type) &&
                Objects.equals(this.nullable, other.nullable) &&
                Objects.equals(this.comment, other.comment) &&
                Objects.equals(this.extraInfo, other.extraInfo) &&
                Objects.equals(this.hidden, other.hidden);
    }

    public static Builder builder()
    {
        return new Builder();
    }

    public static Builder builderFrom(ColumnMetadata columnMetadata)
    {
        return new Builder(columnMetadata);
    }

    public static class Builder
    {
        private String name;
        private PGType<?> type;
        private boolean nullable = true;
        private Optional<String> comment = Optional.empty();

        private String value;
        private Optional<String> extraInfo = Optional.empty();
        private boolean hidden;
        private Map<String, Object> properties = emptyMap();

        private Builder() {}

        private Builder(ColumnMetadata columnMetadata)
        {
            this.name = columnMetadata.getName();
            this.type = columnMetadata.getType();
            this.nullable = columnMetadata.isNullable();
            this.comment = columnMetadata.getComment();
            this.value = columnMetadata.getValue();
            this.extraInfo = columnMetadata.getExtraInfo();
            this.hidden = columnMetadata.isHidden();
            this.properties = columnMetadata.getProperties();
        }

        public Builder setName(String name)
        {
            this.name = requireNonNull(name, "name is null");
            return this;
        }

        public Builder setType(PGType<?> type)
        {
            this.type = requireNonNull(type, "type is null");
            return this;
        }

        public Builder setNullable(boolean nullable)
        {
            this.nullable = nullable;
            return this;
        }

        public Builder setComment(Optional<String> comment)
        {
            this.comment = requireNonNull(comment, "comment is null");
            return this;
        }

        public Builder setValue(String value)
        {
            this.value = value;
            return this;
        }

        public Builder setExtraInfo(Optional<String> extraInfo)
        {
            this.extraInfo = requireNonNull(extraInfo, "extraInfo is null");
            return this;
        }

        public Builder setHidden(boolean hidden)
        {
            this.hidden = hidden;
            return this;
        }

        public Builder setProperties(Map<String, Object> properties)
        {
            this.properties = requireNonNull(properties, "properties is null");
            return this;
        }

        public ColumnMetadata build()
        {
            return new ColumnMetadata(
                    name,
                    type,
                    nullable,
                    comment.orElse(null),
                    value,
                    extraInfo.orElse(null),
                    hidden,
                    properties);
        }
    }
}
