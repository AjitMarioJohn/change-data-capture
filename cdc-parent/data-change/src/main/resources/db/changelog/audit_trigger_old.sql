create or replace
function wikimedia_events_audit_trigger_func()
returns trigger as $$
declare
    v_operation VARCHAR(10);

begin
    if TG_OP = 'INSERT' then
        v_operation := 'INSERT';

elsif TG_OP = 'UPDATE' then
        v_operation := 'UPDATE';

elsif TG_OP = 'DELETE' then
        v_operation := 'DELETE';
end if;

-- Get the partition_key_id from another table
   SELECT instance_id INTO v_partition_key_id  FROM allotment a ORDER BY RANDOM() LIMIT 1;

insert
	into
	wikimedia_events_audit (id,
	created_at,
	event_time,
	title,
	title_url,
	updated_at,
	user_name,
	operation,
	partition_key)
values (NEW.id,
NEW.created_at,
NEW.event_time,
NEW.title,
NEW.title_url,
NEW.updated_at,
NEW.user_name,
v_operation,
v_partition_key_id);

return new;
end;

$$ language plpgsql;

create trigger wikimedia_events_audit_trigger
after
insert
	or
update
	or
delete
	on
	wikimedia_events
for each row execute function wikimedia_events_audit_trigger_func();