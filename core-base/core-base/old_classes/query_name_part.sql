select * from (
select  distinct  art.name  , art.id from 
artist art inner join 
artist_name_part_rel rel
on art.id = rel.artist_id
inner join  artist_name_part part 
on rel.part_id = part.id 
where exists (
	select rel1.artist_id 
	from  artist_name_part part
inner join artist_name_part_rel rel1 
on rel1.part_id = part.id  
where part.name_part = 'Saxon'
and rel.artist_id = rel1.artist_id)
) as t 
