SELECT relname, pg_size_pretty(pg_relation_size(C.oid))
FROM pg_class C
LEFT JOIN pg_namespace N ON (N.oid = C.relnamespace)
WHERE nspname NOT IN ('pg_catalog', 'information_schema', 'pg_toast') AND relkind = 'i';
