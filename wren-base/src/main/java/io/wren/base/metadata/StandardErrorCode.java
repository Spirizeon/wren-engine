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

package io.wren.base.metadata;

import io.wren.base.ErrorCode;
import io.wren.base.ErrorCodeSupplier;
import io.wren.base.ErrorType;

public enum StandardErrorCode
        implements ErrorCodeSupplier
{
    GENERIC_USER_ERROR(0, ErrorType.USER_ERROR),
    SYNTAX_ERROR(1, ErrorType.USER_ERROR),
    ABANDONED_QUERY(2, ErrorType.USER_ERROR),
    USER_CANCELED(3, ErrorType.USER_ERROR),
    PERMISSION_DENIED(4, ErrorType.USER_ERROR),
    NOT_FOUND(5, ErrorType.USER_ERROR),
    FUNCTION_NOT_FOUND(6, ErrorType.USER_ERROR),
    INVALID_FUNCTION_ARGUMENT(7, ErrorType.USER_ERROR),             // caught by TRY
    DIVISION_BY_ZERO(8, ErrorType.USER_ERROR),                      // caught by TRY
    INVALID_CAST_ARGUMENT(9, ErrorType.USER_ERROR),                 // caught by TRY
    OPERATOR_NOT_FOUND(10, ErrorType.USER_ERROR),
    INVALID_VIEW(11, ErrorType.USER_ERROR),
    ALREADY_EXISTS(12, ErrorType.USER_ERROR),
    NOT_SUPPORTED(13, ErrorType.USER_ERROR),
    INVALID_SESSION_PROPERTY(14, ErrorType.USER_ERROR),
    INVALID_WINDOW_FRAME(15, ErrorType.USER_ERROR),
    CONSTRAINT_VIOLATION(16, ErrorType.USER_ERROR),
    TRANSACTION_CONFLICT(17, ErrorType.USER_ERROR),
    INVALID_TABLE_PROPERTY(18, ErrorType.USER_ERROR),
    NUMERIC_VALUE_OUT_OF_RANGE(19, ErrorType.USER_ERROR),            // caught by TRY
    UNKNOWN_TRANSACTION(20, ErrorType.USER_ERROR),
    NOT_IN_TRANSACTION(21, ErrorType.USER_ERROR),
    TRANSACTION_ALREADY_ABORTED(22, ErrorType.USER_ERROR),
    READ_ONLY_VIOLATION(23, ErrorType.USER_ERROR),
    MULTI_CATALOG_WRITE_CONFLICT(24, ErrorType.USER_ERROR),
    AUTOCOMMIT_WRITE_CONFLICT(25, ErrorType.USER_ERROR),
    UNSUPPORTED_ISOLATION_LEVEL(26, ErrorType.USER_ERROR),
    INCOMPATIBLE_CLIENT(27, ErrorType.USER_ERROR),
    SUBQUERY_MULTIPLE_ROWS(28, ErrorType.USER_ERROR),
    PROCEDURE_NOT_FOUND(29, ErrorType.USER_ERROR),
    INVALID_PROCEDURE_ARGUMENT(30, ErrorType.USER_ERROR),
    QUERY_REJECTED(31, ErrorType.USER_ERROR),
    AMBIGUOUS_FUNCTION_CALL(32, ErrorType.USER_ERROR),
    INVALID_SCHEMA_PROPERTY(33, ErrorType.USER_ERROR),
    SCHEMA_NOT_EMPTY(34, ErrorType.USER_ERROR),
    QUERY_TEXT_TOO_LARGE(35, ErrorType.USER_ERROR),
    UNSUPPORTED_SUBQUERY(36, ErrorType.USER_ERROR),
    EXCEEDED_FUNCTION_MEMORY_LIMIT(37, ErrorType.USER_ERROR),
    ADMINISTRATIVELY_KILLED(38, ErrorType.USER_ERROR),
    INVALID_COLUMN_PROPERTY(39, ErrorType.USER_ERROR),
    QUERY_HAS_TOO_MANY_STAGES(40, ErrorType.USER_ERROR),
    INVALID_SPATIAL_PARTITIONING(41, ErrorType.USER_ERROR),
    INVALID_ANALYZE_PROPERTY(42, ErrorType.USER_ERROR),
    TYPE_NOT_FOUND(43, ErrorType.USER_ERROR),
    CATALOG_NOT_FOUND(44, ErrorType.USER_ERROR),
    SCHEMA_NOT_FOUND(45, ErrorType.USER_ERROR),
    TABLE_NOT_FOUND(46, ErrorType.USER_ERROR),
    COLUMN_NOT_FOUND(47, ErrorType.USER_ERROR),
    ROLE_NOT_FOUND(48, ErrorType.USER_ERROR),
    SCHEMA_ALREADY_EXISTS(49, ErrorType.USER_ERROR),
    TABLE_ALREADY_EXISTS(50, ErrorType.USER_ERROR),
    COLUMN_ALREADY_EXISTS(51, ErrorType.USER_ERROR),
    ROLE_ALREADY_EXISTS(52, ErrorType.USER_ERROR),
    DUPLICATE_NAMED_QUERY(53, ErrorType.USER_ERROR),
    DUPLICATE_COLUMN_NAME(54, ErrorType.USER_ERROR),
    MISSING_COLUMN_NAME(55, ErrorType.USER_ERROR),
    MISSING_CATALOG_NAME(56, ErrorType.USER_ERROR),
    MISSING_SCHEMA_NAME(57, ErrorType.USER_ERROR),
    TYPE_MISMATCH(58, ErrorType.USER_ERROR),
    INVALID_LITERAL(59, ErrorType.USER_ERROR),
    COLUMN_TYPE_UNKNOWN(60, ErrorType.USER_ERROR),
    MISMATCHED_COLUMN_ALIASES(61, ErrorType.USER_ERROR),
    AMBIGUOUS_NAME(62, ErrorType.USER_ERROR),
    INVALID_COLUMN_REFERENCE(63, ErrorType.USER_ERROR),
    MISSING_GROUP_BY(64, ErrorType.USER_ERROR),
    MISSING_ORDER_BY(65, ErrorType.USER_ERROR),
    MISSING_OVER(66, ErrorType.USER_ERROR),
    NESTED_AGGREGATION(67, ErrorType.USER_ERROR),
    NESTED_WINDOW(68, ErrorType.USER_ERROR),
    EXPRESSION_NOT_IN_DISTINCT(69, ErrorType.USER_ERROR),
    TOO_MANY_GROUPING_SETS(70, ErrorType.USER_ERROR),
    FUNCTION_NOT_WINDOW(71, ErrorType.USER_ERROR),
    FUNCTION_NOT_AGGREGATE(72, ErrorType.USER_ERROR),
    EXPRESSION_NOT_AGGREGATE(73, ErrorType.USER_ERROR),
    EXPRESSION_NOT_SCALAR(74, ErrorType.USER_ERROR),
    EXPRESSION_NOT_CONSTANT(75, ErrorType.USER_ERROR),
    INVALID_ARGUMENTS(76, ErrorType.USER_ERROR),
    TOO_MANY_ARGUMENTS(77, ErrorType.USER_ERROR),
    INVALID_PRIVILEGE(78, ErrorType.USER_ERROR),
    DUPLICATE_PROPERTY(79, ErrorType.USER_ERROR),
    INVALID_PARAMETER_USAGE(80, ErrorType.USER_ERROR),
    VIEW_IS_STALE(81, ErrorType.USER_ERROR),
    VIEW_IS_RECURSIVE(82, ErrorType.USER_ERROR),
    NULL_TREATMENT_NOT_ALLOWED(83, ErrorType.USER_ERROR),
    INVALID_ROW_FILTER(84, ErrorType.USER_ERROR),
    INVALID_COLUMN_MASK(85, ErrorType.USER_ERROR),
    MISSING_TABLE(86, ErrorType.USER_ERROR),
    INVALID_RECURSIVE_REFERENCE(87, ErrorType.USER_ERROR),
    MISSING_COLUMN_ALIASES(88, ErrorType.USER_ERROR),
    NESTED_RECURSIVE(89, ErrorType.USER_ERROR),
    INVALID_LIMIT_CLAUSE(90, ErrorType.USER_ERROR),
    INVALID_ORDER_BY(91, ErrorType.USER_ERROR),
    DUPLICATE_WINDOW_NAME(92, ErrorType.USER_ERROR),
    INVALID_WINDOW_REFERENCE(93, ErrorType.USER_ERROR),
    INVALID_PARTITION_BY(94, ErrorType.USER_ERROR),

    GENERIC_INTERNAL_ERROR(65536, ErrorType.INTERNAL_ERROR),
    TOO_MANY_REQUESTS_FAILED(65537, ErrorType.INTERNAL_ERROR),
    PAGE_TOO_LARGE(65538, ErrorType.INTERNAL_ERROR),
    PAGE_TRANSPORT_ERROR(65539, ErrorType.INTERNAL_ERROR),
    PAGE_TRANSPORT_TIMEOUT(65540, ErrorType.INTERNAL_ERROR),
    NO_NODES_AVAILABLE(65541, ErrorType.INTERNAL_ERROR),
    REMOTE_TASK_ERROR(65542, ErrorType.INTERNAL_ERROR),
    COMPILER_ERROR(65543, ErrorType.INTERNAL_ERROR),
    REMOTE_TASK_MISMATCH(65544, ErrorType.INTERNAL_ERROR),
    SERVER_SHUTTING_DOWN(65545, ErrorType.INTERNAL_ERROR),
    FUNCTION_IMPLEMENTATION_MISSING(65546, ErrorType.INTERNAL_ERROR),
    REMOTE_BUFFER_CLOSE_FAILED(65547, ErrorType.INTERNAL_ERROR),
    SERVER_STARTING_UP(65548, ErrorType.INTERNAL_ERROR),
    FUNCTION_IMPLEMENTATION_ERROR(65549, ErrorType.INTERNAL_ERROR),
    INVALID_PROCEDURE_DEFINITION(65550, ErrorType.INTERNAL_ERROR),
    PROCEDURE_CALL_FAILED(65551, ErrorType.INTERNAL_ERROR),
    AMBIGUOUS_FUNCTION_IMPLEMENTATION(65552, ErrorType.INTERNAL_ERROR),
    ABANDONED_TASK(65553, ErrorType.INTERNAL_ERROR),
    CORRUPT_SERIALIZED_IDENTITY(65554, ErrorType.INTERNAL_ERROR),
    CORRUPT_PAGE(65555, ErrorType.INTERNAL_ERROR),
    OPTIMIZER_TIMEOUT(65556, ErrorType.INTERNAL_ERROR),
    OUT_OF_SPILL_SPACE(65557, ErrorType.INTERNAL_ERROR),
    REMOTE_HOST_GONE(65558, ErrorType.INTERNAL_ERROR),
    CONFIGURATION_INVALID(65559, ErrorType.INTERNAL_ERROR),
    CONFIGURATION_UNAVAILABLE(65560, ErrorType.INTERNAL_ERROR),
    INVALID_RESOURCE_GROUP(65561, ErrorType.INTERNAL_ERROR),
    SERIALIZATION_ERROR(65562, ErrorType.INTERNAL_ERROR),

    GENERIC_INSUFFICIENT_RESOURCES(131072, ErrorType.INSUFFICIENT_RESOURCES),
    EXCEEDED_GLOBAL_MEMORY_LIMIT(131073, ErrorType.INSUFFICIENT_RESOURCES),
    QUERY_QUEUE_FULL(131074, ErrorType.INSUFFICIENT_RESOURCES),
    EXCEEDED_TIME_LIMIT(131075, ErrorType.INSUFFICIENT_RESOURCES),
    CLUSTER_OUT_OF_MEMORY(131076, ErrorType.INSUFFICIENT_RESOURCES),
    EXCEEDED_CPU_LIMIT(131077, ErrorType.INSUFFICIENT_RESOURCES),
    EXCEEDED_SPILL_LIMIT(131078, ErrorType.INSUFFICIENT_RESOURCES),
    EXCEEDED_LOCAL_MEMORY_LIMIT(131079, ErrorType.INSUFFICIENT_RESOURCES),
    ADMINISTRATIVELY_PREEMPTED(131080, ErrorType.INSUFFICIENT_RESOURCES),
    EXCEEDED_SCAN_LIMIT(131081, ErrorType.INSUFFICIENT_RESOURCES),

    /**/;

    // Connectors can use error codes starting at the range 0x0100_0000
    // See https://github.com/trinodb/trino/wiki/Error-Codes

    private final ErrorCode errorCode;

    StandardErrorCode(int code, ErrorType type)
    {
        errorCode = new ErrorCode(code, name(), type);
    }

    @Override
    public ErrorCode toErrorCode()
    {
        return errorCode;
    }
}
