declare
   quantidade integer; 
   v_name character varying(1000);
   a_row RECORD;
   a_cursor CURSOR FOR SELECT "name" FROM artist;
   v_part character varying(255);
   name_row RECORD;
   name_cursor CURSOR FOR SELECT part 
		from (
		 select 
		 initcap(regexp_split_to_table(v_name, E'\\s+')) as part
		) as t;
begin
    quantidade := 0; 
	open a_cursor;
	LOOP
	FETCH  a_cursor INTO a_row;
	EXIT WHEN NOT FOUND;
		BEGIN
			v_name := a_row.name;
			--RAISE NOTICE 'inserting line %', v_name;
				open name_cursor; 
				loop
					fetch name_cursor into name_row;
					exit when not found;
						begin
							 v_part := cast(name_row.part as character varying(255));  
							 if not exists (select 1 from artist_name_part part where part.name_part = v_part) then
								 insert into artist_name_part
								 (name_part)
								 values
								 (v_part);
							 end if;
						end; 
				end loop;
				close name_cursor;
		END;
	END LOOP;
	close a_cursor;
	return quantidade;
end;