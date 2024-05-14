create table area (id  bigserial not null, area_id int8 not null, name VARCHAR NOT NULL not null, gid UUID NOT NULL not null, areaType_id int8 not null, primary key (id))
create table area_begin_date_join (date_id int8, area_id int8 not null, primary key (area_id))
create table area_comment_join (comment_id int8, area_id int8 not null, primary key (area_id))
create table area_end_date_join (date_id int8, area_id int8 not null, primary key (area_id))
create table AreaComment (id  bigserial not null, comment VARCHAR NOT NULL not null, primary key (id))
create table artist (id  bigserial not null, artist_id int8 not null, name VARCHAR NOT NULL not null, gid UUID NOT NULL not null, artistType_id int8 not null, primary key (id))
create table artist_alias (id  bigserial not null, artist_alias_id int8 not null, name VARCHAR NOT NULL not null, primary key (id))
create table artist_alias_begin_date_join (date_id int8, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_alias_end_date_join (date_id int8, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_alias_join (artist_alias_id int8 not null, artist_id int8 not null, primary key (artist_id))
create table artist_alias_locale_join (locale_id int8, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_alias_sortname_join (sort_name_id int8 not null, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_alias_type_join (alias_type_id int8 not null, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_area_join (area_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_begin_area_join (area_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_begin_date_join (date_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_comment_join (comment_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_credit (id  bigserial not null, artist_credit_id int8 not null, name VARCHAR NOT NULL not null, primary key (id))
create table artist_credit_count_join (artist_count_id int8 not null, artist_credit_id int8 not null, primary key (artist_credit_id))
create table artist_credit_name_position_join (position_id int8, artist_credit_name int8 not null, primary key (artist_credit_name))
create table artist_credit_name_rel (artist_credit_id int8 not null, artist_name_id int8 not null)
create table artist_end_area_join (area_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_end_date_join (date_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_gender_join (gender_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_ref_count_join (artist_refcount_id int8 not null, artist_credit_id int8 not null, primary key (artist_credit_id))
create table artist_sortname_join (sort_name_id int8 not null, artist_id int8 not null, primary key (artist_id))
create table ArtistComment (id  bigserial not null, comment VARCHAR NOT NULL not null, primary key (id))
create table ArtistCreditedName (id  bigserial not null, artist_credit_name_join_phrase TEXT DEFAULT '', name VARCHAR NOT NULL not null, artistid int8, artist_credit_id int8, primary key (id))
create table bar_code_base (table_id VARCHAR NOT NULL not null, id  bigserial not null, bar_code varchar(255) not null, primary key (id))
create table base_type (table_id VARCHAR NOT NULL not null, id  bigserial not null, gid UUID NOT NULL not null, name VARCHAR NOT NULL not null, childOrder int8, parent int8, type_id int8 not null, primary key (id))
create table base_type_description_join (description_id int8, base_type_id int8 not null, primary key (base_type_id))
create table begin_dates (class_name VARCHAR NOT NULL not null, id  bigserial not null, day SMALLINT NOT NULL not null, month SMALLINT NOT NULL not null, year SMALLINT NOT NULL not null, primary key (id))
create table description_base (table_id VARCHAR NOT NULL not null, id  bigserial not null, description VARCHAR NOT NULL not null, primary key (id))
create table end_dates (class_name VARCHAR NOT NULL not null, id  bigserial not null, end_day SMALLINT NOT NULL not null, end_month SMALLINT NOT NULL not null, end_year SMALLINT NOT NULL not null, primary key (id))
create table instrument (id  bigserial not null, gid UUID NOT NULL not null, instrument_id int8 not null, name VARCHAR NOT NULL not null, instrumentType_id int8 not null, primary key (id))
create table instrument_comment_join (comment_id int8, instrument_id int8 not null, primary key (instrument_id))
create table instrument_description_join (description_id int8, instrument_id int8 not null, primary key (instrument_id))
create table InstrumentComment (id  bigserial not null, comment VARCHAR NOT NULL not null, primary key (id))
create table isrc (id  bigserial not null, isrc CHAR(12) NOT NULL CHECK (isrc ~ E'^[A-Z]{2}[A-Z0-9]{3}[0-9]{7}$') not null, isrc_id int8, source int4, primary key (id))
create table isrc_recording_join (recording_id int8 not null, isrc_id int8 not null, primary key (isrc_id))
create table label (id  bigserial not null, gid UUID NOT NULL not null, labelCode int4, label_id int8, name VARCHAR NOT NULL not null, area_id int8, label_begin_date_id int8, label_end_date_id int8, primary key (id))
create table label_type_join (label_type_id int8, label_id int8 not null, primary key (label_id))
create table language (id  bigserial not null, frequency int8, isoCode1 varchar(2), isoCode2B varchar(3), isoCode2T varchar(3), isoCode3 varchar(3), language_id int8, name varchar(100) not null, primary key (id))
create table locale_base (table_id VARCHAR NOT NULL not null, id  bigserial not null, locale VARCHAR NOT NULL, primary key (id))
create table long_count_base (table_id VARCHAR NOT NULL not null, id  bigserial not null, count int8 not null, primary key (id))
create table long_number_base (table_id VARCHAR NOT NULL not null, id  bigserial not null, number int8 not null, primary key (id))
create table long_position_base (class_id VARCHAR NOT NULL not null, id  bigserial not null, position int8 not null, primary key (id))
create table Medium (id  bigserial not null, medium_id int8, name VARCHAR NOT NULL not null, medium_format_id int8, release_id int8 not null, primary key (id))
create table medium_count_join (count_id int8, medium_id int8 not null, primary key (medium_id))
create table medium_format (id  bigserial not null, description varchar(4000), hasDiscId varchar(6) not null, gid uuid not null, medium_format_id int8 not null, name VARCHAR NOT NULL not null, parent int8, year int4, primary key (id))
create table medium_position_join (position_id int8, medium_id int8 not null, primary key (medium_id))
create table ralias_sortname_join (sort_name_id int8 not null, ralias_id int8 not null, primary key (ralias_id))
create table recording (id  bigserial not null, gid UUID NOT NULL not null, recording_id int8 not null, name VARCHAR NOT NULL not null, artist_credit_id int8 not null, recordingLength_id int8, primary key (id))
create table recording_alias (id  bigserial not null, begin_date_day int4, begin_date_month int4, begin_date_year int4, end_date_day int4, end_date_month int4, end_date_year int4, locale VARCHAR NOT NULL, recording_alias_id int8 not null, name VARCHAR NOT NULL not null, recording_id int8 not null, recording_type_id int8 not null, primary key (id))
create table recording_comment_join (comment_id int8, recording_id int8 not null, primary key (recording_id))
create table recording_lengthy_base (table_id VARCHAR NOT NULL not null, id  bigserial not null, length int8 not null, primary key (id))
create table RecordingComment (id  bigserial not null, comment VARCHAR NOT NULL not null, primary key (id))
create table release (id  bigserial not null, gid VARCHAR(50) NOT NULL not null, release_id int8 not null, name VARCHAR NOT NULL not null, artist_credit_id int8, primary key (id))
create table release_alias (id  bigserial not null, relase_alias_id int8 not null, name VARCHAR NOT NULL not null, release_id int8 not null, type_id int8 not null, primary key (id))
create table release_alias_begin_date_join (date_id int8, release_alias_id int8 not null, primary key (release_alias_id))
create table release_alias_end_date_join (date_id int8, release_alias_id int8 not null, primary key (release_alias_id))
create table release_alias_locale_join (locale_id int8, release_alias_id int8 not null, primary key (release_alias_id))
create table release_alias_sortname_join (sortname_id int8, release_alias_id int8 not null, primary key (release_alias_id))
create table release_barcode_join (barcode_id int8, release_id int8 not null, primary key (release_id))
create table release_catalog_join (catalog_id int8, release_label_id int8 not null, primary key (release_label_id))
create table release_comment_join (barcode_id int8, release_id int8 not null, primary key (release_id))
create table release_group (id  bigserial not null, gid UUID NOT NULL not null, release_group_id int8 not null, name VARCHAR NOT NULL not null, artist_credit_id int8 not null, type_id int8, primary key (id))
create table release_group_comment_join (comment_id int8, release_group_id int8 not null, primary key (release_group_id))
create table release_group_join (release_group_id int8, release_id int8 not null, primary key (release_id))
create table release_label (id  bigserial not null, release_label_id int8 not null, release_id int8 not null, primary key (id))
create table release_label_catalog (id  bigserial not null, catalog_number VARCHAR NOT NULL not null, primary key (id))
create table release_language_join (language_id int8, release_id int8 not null, primary key (release_id))
create table release_packaging (id  bigserial not null, gid UUID NOT NULL not null, release_packaging_id int8 not null, name VARCHAR NOT NULL not null, primary key (id))
create table release_packaging_join (packaging_id int8, release_id int8 not null, primary key (release_id))
create table release_status (id  bigserial not null, gid UUID NOT NULL not null, release_status_id int8 not null, name VARCHAR NOT NULL not null, primary key (id))
create table release_status_join (status_id int8, release_id int8 not null, primary key (release_id))
create table ReleaseComment (id  bigserial not null, comment VARCHAR NOT NULL not null, primary key (id))
create table ReleaseGroupComment (id  bigserial not null, comment VARCHAR NOT NULL not null, primary key (id))
create table releaselable_label_join (label_id int8 not null, release_label_id int8 not null, primary key (release_label_id))
create table sort_name_base (table_id VARCHAR NOT NULL not null, id  bigserial not null, sort_name VARCHAR NOT NULL not null, primary key (id))
create table string_number_base (table_id VARCHAR NOT NULL not null, id  bigserial not null, number VARCHAR NOT NULL, primary key (id))
create table track (id  bigserial not null, gid UUID NOT NULL, track_id int8, name VARCHAR NOT NULL, recordingId int8 not null, primary key (id))
create table track_length_join (length_id int8, track_id int8 not null, primary key (track_id))
create table track_number_join (number_id int8, track_id int8 not null, primary key (track_id))
create table track_position_join (position_id int8, track_id int8 not null, primary key (track_id))
create table TrackLength (id  bigserial not null, length int8 not null, primary key (id))
create table work (id  bigserial not null, gid UUID NOT NULL not null, work_id int8 not null, name VARCHAR NOT NULL not null, workType_id int8 not null, primary key (id))
create table work_comment_join (comment_id int8, work_id int8 not null, primary key (work_id))
create table WorkComment (id  bigserial not null, comment VARCHAR NOT NULL not null, primary key (id))
create index idx_area_id on area (area_id)
alter table area add constraint uk_area_id unique (area_id)
create index idex_artist_id on artist (artist_id)
create index idx_artist_alias_id on artist_alias (artist_alias_id)
alter table artist_alias_sortname_join add constraint UK_pj758kjmvfi4w6iylmqqyf87y unique (sort_name_id)
create index idx_artist_credit_id on artist_credit (artist_credit_id)
alter table artist_credit_count_join add constraint UK_b1e6iatyc0a9ebcsnt02cmy6v unique (artist_count_id)
alter table artist_ref_count_join add constraint UK_cjp45m8pm8reo0nj52n626alp unique (artist_refcount_id)
alter table artist_sortname_join add constraint UK_tcbnii28trkvjw0for5lvukji unique (sort_name_id)
create index bar_code_idx on bar_code_base (bar_code)
create index base_type_id_idx on base_type (type_id)
create index begin_date_year_idx on begin_dates (year)
create index description_table_idx on description_base (description)
create index end_date_year_idx on end_dates (end_year)
create index idx_instrument_id on instrument (instrument_id)
alter table instrument add constraint uk_instrument_id unique (instrument_id)
create index idx_isrc_id on isrc (isrc_id)
alter table isrc add constraint uk_isrc_id unique (isrc_id)
alter table label add constraint uk_label_id unique (label_id)
create index index_language_id on language (language_id)
alter table language add constraint uk_language_id unique (language_id)
create index locale_table_idx on locale_base (locale)
create index long_count_field_idx on long_count_base (count)
create index long_position_position_idx on long_position_base (position)
create index idx_medium_id on Medium (medium_id)
alter table Medium add constraint uk_medium_id unique (medium_id)
alter table medium_format add constraint uk_medium_format_id unique (medium_format_id)
alter table ralias_sortname_join add constraint UK_tanxi0a8ud0dsd29ibeg741i unique (sort_name_id)
create index idx_recording_id on recording (recording_id)
alter table recording add constraint uk_recording_id unique (recording_id)
create index idx_recording_alias_id on recording_alias (recording_alias_id)
alter table recording_alias add constraint uk_recording_alias_id unique (recording_alias_id)
create index long_length_table_idx on recording_lengthy_base (length)
create index idx_release_id on release (release_id)
alter table release add constraint uk_release_id unique (release_id)
alter table release_alias add constraint uk_release_alias_id unique (relase_alias_id)
create index idx_release_group_id on release_group (release_group_id)
alter table release_group add constraint uk_release_group_id unique (release_group_id)
create index idx_release_label_id on release_label (release_label_id)
alter table release_label add constraint uk_release_label_id unique (release_label_id)
create index release_l_catalog_number_idx on release_label_catalog (catalog_number)
create index idx_release_pack_id on release_packaging (release_packaging_id)
alter table release_packaging add constraint uk_release_packaging_id unique (release_packaging_id)
create index idx_release_status_id on release_status (release_status_id)
alter table release_status add constraint uk_release_status_id unique (release_status_id)
create index sort_name_idx on sort_name_base (sort_name)
create index string_number_number_idx on string_number_base (number)
alter table track add constraint uk_track_id unique (track_id)
create index idx_work_id on work (work_id)
alter table work add constraint uk_work_id unique (work_id)
alter table area add constraint FKkh7fglo0ew1ivq3g17l0v3xb9 foreign key (areaType_id) references base_type
alter table area_begin_date_join add constraint FK6ejxgqiu1outyxf4qd2xqjfmj foreign key (date_id) references begin_dates
alter table area_begin_date_join add constraint FKhxwhfi68h7pm6nw99q60kulwx foreign key (area_id) references area
alter table area_comment_join add constraint FKa87l53y4i7o3ifexsbvp5a299 foreign key (comment_id) references AreaComment
alter table area_comment_join add constraint FKifb9goxbil6mp8qnlc652fg1o foreign key (area_id) references area
alter table area_end_date_join add constraint FKn5c0biposrf655qxo4s7hqk9i foreign key (date_id) references end_dates
alter table area_end_date_join add constraint FK2to1rsv7q6jo3oh51adue24sq foreign key (area_id) references area
alter table artist add constraint FKnh2erfii5bjj6jjcbboporlhy foreign key (artistType_id) references base_type
alter table artist_alias_begin_date_join add constraint FK161ni63md650a9ws8jl6xsln6 foreign key (date_id) references begin_dates
alter table artist_alias_begin_date_join add constraint FKpks05cos5wm1vmqr2pyjt97sf foreign key (artist_alias_id) references artist_alias
alter table artist_alias_end_date_join add constraint FKhvbnqkhq3q16g955k1uvepvxn foreign key (date_id) references end_dates
alter table artist_alias_end_date_join add constraint FKmug4f2wcro7htgp48a4iwycu8 foreign key (artist_alias_id) references artist_alias
alter table artist_alias_join add constraint FKgdwpq8ctk6a39mvnvsvo2fc5i foreign key (artist_alias_id) references artist
alter table artist_alias_join add constraint FK48r5krvgiw047ms72erpsxkju foreign key (artist_id) references artist_alias
alter table artist_alias_locale_join add constraint FKb6xi7sb9l8h80v4qdb52gewf2 foreign key (locale_id) references locale_base
alter table artist_alias_locale_join add constraint FKtle5378eltgllln6n2jimbrrd foreign key (artist_alias_id) references artist_alias
alter table artist_alias_sortname_join add constraint FKtnhfe96x3gvwohh93u88harun foreign key (sort_name_id) references sort_name_base
alter table artist_alias_sortname_join add constraint FKaljjsn3xplko82gxwoyvnhhdg foreign key (artist_alias_id) references artist_alias
alter table artist_alias_type_join add constraint FK27j0hv1oggic0lv0fkjttcage foreign key (alias_type_id) references base_type
alter table artist_alias_type_join add constraint FKp27ue68w73pcnl22m8cmvopjk foreign key (artist_alias_id) references artist_alias
alter table artist_area_join add constraint FK5v2pynxsg5phk81a9a888f8p6 foreign key (area_id) references area
alter table artist_area_join add constraint FKjwws3jf2jrltwhscmyr4c2c8d foreign key (artist_id) references artist
alter table artist_begin_area_join add constraint FKlib6g7ixl78iper28hwyp4b6j foreign key (area_id) references area
alter table artist_begin_area_join add constraint FKdsqb47nv5mkfer33g0uwjxrg3 foreign key (artist_id) references artist
alter table artist_begin_date_join add constraint FKkb756u3kpj2k4f9o3ar54uk5b foreign key (date_id) references begin_dates
alter table artist_begin_date_join add constraint FK9ep35foih1qqx5cvjt9lwa1gy foreign key (artist_id) references artist
alter table artist_comment_join add constraint FK5sh23cs6j9tv0i8yta3nldxpw foreign key (comment_id) references ArtistComment
alter table artist_comment_join add constraint FKtqvsp3t5j61w068n51iowndiw foreign key (artist_id) references artist
alter table artist_credit_count_join add constraint FKf79dl4q17hswqhv9y4mb65qat foreign key (artist_count_id) references long_count_base
alter table artist_credit_count_join add constraint FK9pp1ahlexg9hgppy4p3eeit7y foreign key (artist_credit_id) references artist_credit
alter table artist_credit_name_position_join add constraint FKonl1oigpxdolrcy2vxqi308lr foreign key (position_id) references long_position_base
alter table artist_credit_name_position_join add constraint FK92reeafxnwbh56ucuujcip32m foreign key (artist_credit_name) references ArtistCreditedName
alter table artist_credit_name_rel add constraint FKn3g0jyj028jrwt6ckpuvk1y5j foreign key (artist_name_id) references artist
alter table artist_credit_name_rel add constraint FK44fgvkqt29bcp2ai5oewfl4p1 foreign key (artist_credit_id) references artist_credit
alter table artist_end_area_join add constraint FKdq9xln44yycsfyifwjhpojy5 foreign key (area_id) references area
alter table artist_end_area_join add constraint FKqpmbpcxmsx9horym1828a6nhm foreign key (artist_id) references artist
alter table artist_end_date_join add constraint FKgwa2a78byhej610s4bdwijdy1 foreign key (date_id) references end_dates
alter table artist_end_date_join add constraint FK9xnh1e0g4gykda5onwvpl96w0 foreign key (artist_id) references artist
alter table artist_gender_join add constraint FKgwoqki0iuuqmivpo25dnrjmb1 foreign key (gender_id) references base_type
alter table artist_gender_join add constraint FKhmr79ir0dvn6l6g2k4asxe9rh foreign key (artist_id) references artist
alter table artist_ref_count_join add constraint FKlb2e7nxkf00c41ynywljxmuiy foreign key (artist_refcount_id) references long_count_base
alter table artist_ref_count_join add constraint FKsug54x25nuu957axm0c4obs0u foreign key (artist_credit_id) references artist_credit
alter table artist_sortname_join add constraint FK56a9947m1dr2c1j6l52plkspq foreign key (sort_name_id) references sort_name_base
alter table artist_sortname_join add constraint FK6q14bw7t5sywulcx7pcu6bjcs foreign key (artist_id) references artist
alter table ArtistCreditedName add constraint FKs9buctrdceu49ll5hbk8wddwl foreign key (artistid) references artist
alter table ArtistCreditedName add constraint FKamn4sghaewa0iy4b6y0ysdyej foreign key (artist_credit_id) references artist_credit
alter table base_type_description_join add constraint FKckvph0x1homuj7hn7wver3f8k foreign key (description_id) references description_base
alter table base_type_description_join add constraint FK44wmj6925gqqena2mnr2d3c0e foreign key (base_type_id) references base_type
alter table instrument add constraint FKewxtplg47bkypefa30wk16wkg foreign key (instrumentType_id) references base_type
alter table instrument_comment_join add constraint FKijmebsqk04tiq99kvcdq1y9m3 foreign key (comment_id) references InstrumentComment
alter table instrument_comment_join add constraint FK8dawlfgg0km0ofqhs7k8i10rb foreign key (instrument_id) references instrument
alter table instrument_description_join add constraint FK4hdpovhkn51kl5pmvlj7spj0l foreign key (description_id) references description_base
alter table instrument_description_join add constraint FKkvq50u8tmgigintxinspal853 foreign key (instrument_id) references instrument
alter table isrc_recording_join add constraint FKdst308wec9g1r2pdgl9pu66ba foreign key (recording_id) references recording
alter table isrc_recording_join add constraint FKegxd27v48533e4ioips2v0om8 foreign key (isrc_id) references isrc
alter table label add constraint FKqmabfbh4bw9uowqp733r6f3rb foreign key (area_id) references area
alter table label add constraint FK6u3ob85d3br3xccgqcuds8s7j foreign key (label_begin_date_id) references begin_dates
alter table label add constraint FKr2pu4j9nq9q9v33kq9y23wlmt foreign key (label_end_date_id) references end_dates
alter table label_type_join add constraint FK9ja53dwjckolp8oawxlt9s3gx foreign key (label_type_id) references base_type
alter table label_type_join add constraint FK8epec4y03bpqb2m6fjrb5ae1x foreign key (label_id) references label
alter table Medium add constraint FK88xlaa32r1xxpm9vykwh3675e foreign key (medium_format_id) references medium_format
alter table Medium add constraint FKkhs58g5n5bnyqr14hklutr1jc foreign key (release_id) references release
alter table medium_count_join add constraint FKcgodk0n0yy7b9dh033hkheu72 foreign key (count_id) references long_count_base
alter table medium_count_join add constraint FK5gnqnfnwrqj7uuio47742wsqt foreign key (medium_id) references Medium
alter table medium_position_join add constraint FK7npic7e0xpkw01dji0v59jimt foreign key (position_id) references long_position_base
alter table medium_position_join add constraint FKqpxdjbhc5y2t6sb9g0d6sswg2 foreign key (medium_id) references Medium
alter table ralias_sortname_join add constraint FKp5vufn13fu2xqsrbhqmwiopxe foreign key (sort_name_id) references sort_name_base
alter table ralias_sortname_join add constraint FK25ufn8bacv3csjivgb3q2q3rf foreign key (ralias_id) references recording_alias
alter table recording add constraint FKwhnq53y2fteeexfpar6558ut foreign key (artist_credit_id) references artist_credit
alter table recording add constraint FKp6h8vblmfp7fbvy7t07unr441 foreign key (recordingLength_id) references recording_lengthy_base
alter table recording_alias add constraint FKf3bf0jihug16pfd3n6fksolla foreign key (recording_id) references recording
alter table recording_alias add constraint FKjlmcnxa1hoj9x47ikivshfaq foreign key (recording_type_id) references base_type
alter table recording_comment_join add constraint FKjsf5owqpo6ok6ggkes6d0b3pb foreign key (comment_id) references RecordingComment
alter table recording_comment_join add constraint FK831sby31ef8r0j0lfsfh5nx2b foreign key (recording_id) references recording
alter table release add constraint FKrjxedvgqei5yg4s02538jeu55 foreign key (artist_credit_id) references artist_credit
alter table release_alias add constraint FK8h783sk9tygkr28w7uaw66hgs foreign key (release_id) references release
alter table release_alias add constraint FKgaxu9q2lmc940ikhiq9awjwv2 foreign key (type_id) references base_type
alter table release_alias_begin_date_join add constraint FK2pwnsix504rih7b9c0p2ldcu2 foreign key (date_id) references begin_dates
alter table release_alias_begin_date_join add constraint FK8fwb728l8xka6plxvd5ioam9v foreign key (release_alias_id) references release_alias
alter table release_alias_end_date_join add constraint FK7q6nahiooqujaombbgcbdof2e foreign key (date_id) references end_dates
alter table release_alias_end_date_join add constraint FKh23905t8qli78gtd54skm2k32 foreign key (release_alias_id) references release_alias
alter table release_alias_locale_join add constraint FKfu08i10orqtp60yubiuxco5ug foreign key (locale_id) references locale_base
alter table release_alias_locale_join add constraint FKfi3muh2yf67v2e3aavs7o23iy foreign key (release_alias_id) references release_alias
alter table release_alias_sortname_join add constraint FKk0b8uym87fp1se4cwbp8efudx foreign key (sortname_id) references sort_name_base
alter table release_alias_sortname_join add constraint FK47aqy9lhj7y73lnygfx8i5wbv foreign key (release_alias_id) references release_alias
alter table release_barcode_join add constraint FK11ycaqp6hwmm3vvel4pei3hb3 foreign key (barcode_id) references bar_code_base
alter table release_barcode_join add constraint FK1ohu8rea5r4bflvjfo52wuiak foreign key (release_id) references release
alter table release_catalog_join add constraint FKo6cknv37kwvahw8kip26eaipv foreign key (catalog_id) references release_label_catalog
alter table release_catalog_join add constraint FK5lf20xpd4730xlr6xthgqj3jr foreign key (release_label_id) references release_label
alter table release_comment_join add constraint FKpum7hvj1bfx68kckhqnk068ow foreign key (barcode_id) references ReleaseComment
alter table release_comment_join add constraint FKaso5up8i49s07ak8mqb1w13bo foreign key (release_id) references release
alter table release_group add constraint FKjhugaa7xl4008xcscrx21v8iw foreign key (artist_credit_id) references artist_credit
alter table release_group add constraint FK9co461y510dgpqygvhigvgcko foreign key (type_id) references base_type
alter table release_group_comment_join add constraint FK6q5lnk9mj3q1qr8du8tuvjji9 foreign key (comment_id) references ReleaseGroupComment
alter table release_group_comment_join add constraint FK1b65qv88y0187bhxl42m72kjx foreign key (release_group_id) references release_group
alter table release_group_join add constraint FK9pttnpev0leoq0vx353763urk foreign key (release_group_id) references release_group
alter table release_group_join add constraint FKe8w2lu5p1r45yhxl3jtn9hcua foreign key (release_id) references release
alter table release_label add constraint FKal3q7rkxjcxvpt0yehl9t495x foreign key (release_id) references release
alter table release_language_join add constraint FKk3hvg4y9h946ow3b83bt4xvgo foreign key (language_id) references language
alter table release_language_join add constraint FKa341slnl3qhxi6q6jn432b3yy foreign key (release_id) references release
alter table release_packaging_join add constraint FKx49q7bbkt5gfvxat10i9gyfc foreign key (packaging_id) references release_packaging
alter table release_packaging_join add constraint FK6bi5mttra2txy9rmevfarpqg5 foreign key (release_id) references release
alter table release_status_join add constraint FK5t2s72heb037fymqnesmldax6 foreign key (status_id) references release_status
alter table release_status_join add constraint FKbksyuuyoqsjyd16h15dm0muw foreign key (release_id) references release
alter table releaselable_label_join add constraint FK23bywtxvkwddnuvl7a0385hsv foreign key (label_id) references label
alter table releaselable_label_join add constraint FK89lf519jsmem1p5albfqo9f7v foreign key (release_label_id) references release_label
alter table track add constraint FKcbmq3cor34r184u84jtfhiw1r foreign key (recordingId) references recording
alter table track_length_join add constraint FKclg3rc7jynq5lx20ojjqdlj3 foreign key (length_id) references TrackLength
alter table track_length_join add constraint FK3aj1gvdufrwb9snay9cy91t53 foreign key (track_id) references track
alter table track_number_join add constraint FKmwwixblea4b54nrbc2i3ppbut foreign key (number_id) references string_number_base
alter table track_number_join add constraint FKjfj36ofn0l3no50vutks0ha5x foreign key (track_id) references track
alter table track_position_join add constraint FKf2dsts4egfuibtotwmjbmf3tn foreign key (position_id) references long_position_base
alter table track_position_join add constraint FKptd32j597vii6l8900688trkt foreign key (track_id) references track
alter table work add constraint FK6sdty0tnnwtb8wguhqwc0vnq0 foreign key (workType_id) references base_type
alter table work_comment_join add constraint FKhgg71w0ba92vpbyqpi9lsgtr3 foreign key (comment_id) references WorkComment
alter table work_comment_join add constraint FKmpkos9osoygvrnn497u2utqgv foreign key (work_id) references work
