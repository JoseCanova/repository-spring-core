create sequence sequence_id_seq start 1 increment 50;
create table area (area_id int8 not null, name VARCHAR NOT NULL not null, gid UUID NOT NULL not null, id int8 not null, areaType_id int8 not null, primary key (id));
create table area_begin_date_join (date_id int8, area_id int8 not null, primary key (area_id));
create table area_comment_join (comment_id int8, area_id int8 not null, primary key (area_id));
create table area_end_date_join (date_id int8, area_id int8 not null, primary key (area_id));
create table AreaBeginDate (id int8 not null, primary key (id));
create table AreaComment (comment VARCHAR NOT NULL not null, id int8 not null, primary key (id));
create table AreaEndDate (id int8 not null, primary key (id));
create table AreaType (id int8 not null, primary key (id));
create table artist (artist_id int8 not null, name VARCHAR NOT NULL not null, gid UUID NOT NULL not null, id int8 not null, artistType_id int8 not null, primary key (id));
create table artist_alias (artist_alias_id int8 not null, name VARCHAR NOT NULL not null, id int8 not null, primary key (id));
create table artist_alias_begin_date_join (date_id int8, artist_alias_id int8 not null, primary key (artist_alias_id));
create table artist_alias_end_date_join (date_id int8, artist_alias_id int8 not null, primary key (artist_alias_id));
create table artist_alias_join (artist_alias_id int8 not null, artist_id int8 not null, primary key (artist_id));
create table artist_alias_locale_join (locale_id int8, artist_alias_id int8 not null, primary key (artist_alias_id));
create table artist_alias_sortname_join (sort_name_id int8 not null, artist_alias_id int8 not null, primary key (artist_alias_id));
create table artist_alias_type_join (alias_type_id int8 not null, artist_alias_id int8 not null, primary key (artist_alias_id));
create table artist_area_join (area_id int8, artist_id int8 not null, primary key (artist_id));
create table artist_begin_area_join (area_id int8, artist_id int8 not null, primary key (artist_id));
create table artist_begin_date_join (date_id int8, artist_id int8 not null, primary key (artist_id));
create table artist_comment_join (comment_id int8, artist_id int8 not null, primary key (artist_id));
create table artist_credit (artist_credit_id int8 not null, name VARCHAR NOT NULL not null, id int8 not null, primary key (id));
create table artist_credit_count_join (artist_count_id int8 not null, artist_credit_id int8 not null, primary key (artist_credit_id));
create table artist_credit_name_position_join (position_id int8, artist_credit_name int8 not null, primary key (artist_credit_name));
create table artist_credit_name_rel (artist_credit_id int8 not null, artist_name_id int8 not null);
create table artist_end_area_join (area_id int8, artist_id int8 not null, primary key (artist_id));
create table artist_end_date_join (date_id int8, artist_id int8 not null, primary key (artist_id));
create table artist_gender_join (gender_id int8, artist_id int8 not null, primary key (artist_id));
create table artist_ref_count_join (artist_refcount_id int8 not null, artist_credit_id int8 not null, primary key (artist_credit_id));
create table artist_sortname_join (sort_name_id int8 not null, artist_id int8 not null, primary key (artist_id));
create table ArtistAliasBeginDate (id int8 not null, primary key (id));
create table ArtistAliasEndDate (id int8 not null, primary key (id));
create table ArtistAliasLocale (id int8 not null, primary key (id));
create table ArtistAliasSortName (id int8 not null, primary key (id));
create table ArtistAliasType (id int8 not null, primary key (id));
create table ArtistBeginDate (id int8 not null, primary key (id));
create table ArtistComment (comment VARCHAR NOT NULL not null, id int8 not null, primary key (id));
create table ArtistCreditCount (id int8 not null, primary key (id));
create table ArtistCreditedName (artist_credit_name_join_phrase TEXT NOT NULL DEFAULT '', name VARCHAR NOT NULL not null, id int8 not null, artistid int8, artist_credit_id int8, primary key (id));
create table ArtistCreditedNamePosition (id int8 not null, primary key (id));
create table ArtistCreditRefCount (id int8 not null, primary key (id));
create table ArtistEndDate (id int8 not null, primary key (id));
create table ArtistSortName (id int8 not null, primary key (id));
create table ArtistType (id int8 not null, primary key (id));
create table bar_code_base (bar_code varchar(255) not null, id int8 not null, primary key (id));
create table base_type (gid UUID NOT NULL not null, name VARCHAR NOT NULL not null, childOrder int8, parent int8, type_id int8 not null, id int8 not null, primary key (id));
create table base_type_description_join (description_id int8, base_type_id int8 not null, primary key (base_type_id));
create table BaseTypeDescription (id int8 not null, primary key (id));
create table begin_dates (day SMALLINT NOT NULL not null, month SMALLINT NOT NULL not null, year SMALLINT NOT NULL not null, id int8 not null, primary key (id));
create table description_base (description VARCHAR NOT NULL not null, id int8 not null, primary key (id));
create table end_dates (end_day SMALLINT NOT NULL not null, end_month SMALLINT NOT NULL not null, end_year SMALLINT NOT NULL not null, id int8 not null, primary key (id));
create table Gender (id int8 not null, primary key (id));
create table instrument (gid UUID NOT NULL not null, instrument_id int8 not null, name VARCHAR NOT NULL not null, id int8 not null, instrumentType_id int8 not null, primary key (id));
create table instrument_comment_join (comment_id int8, instrument_id int8 not null, primary key (instrument_id));
create table instrument_description_join (description_id int8, instrument_id int8 not null, primary key (instrument_id));
create table InstrumentComment (comment VARCHAR NOT NULL not null, id int8 not null, primary key (id));
create table InstrumentDescription (id int8 not null, primary key (id));
create table InstrumentType (id int8 not null, primary key (id));
create table isrc (ISRC varchar(255), ISRC_ID int8, RECORDING_ID int8, SOURCE varchar(255), id int8 not null, primary key (id));
create table label (gid UUID NOT NULL not null, labelCode int4, labelId int8, name VARCHAR NOT NULL not null, id int8 not null, area_id int8, label_begin_date_id int8, label_end_date_id int8, labelType_id int8, primary key (id));
create table LabelBeginDate (id int8 not null, primary key (id));
create table LabelEndDate (id int8 not null, primary key (id));
create table LabelType (id int8 not null, primary key (id));
create table language (frequency int8, isoCode1 varchar(2), isoCode2B varchar(3), isoCode2T varchar(3), isoCode3 varchar(3), language_id int8, id int8 not null, primary key (id));
create table locale_base (locale VARCHAR NOT NULL, id int8 not null, primary key (id));
create table long_count_base (count int8 not null, id int8 not null, primary key (id));
create table long_number_base (number int8 not null, id int8 not null, primary key (id));
create table long_position_base (position int8 not null, id int8 not null, primary key (id));
create table LongIdSortName (name VARCHAR NOT NULL not null, sort_name VARCHAR NOT NULL not null, id int8 not null, primary key (id));
create table Medium (medium_id int8, name VARCHAR NOT NULL not null, id int8 not null, medium_format_id int8, release_id int8 not null, primary key (id));
create table medium_count_join (count_id int8 not null, medium_id int8 not null, primary key (medium_id));
create table medium_format (description varchar(4000), gid uuid not null, hasDiscId varchar(6) not null, mediumFormatId int8 not null, name VARCHAR NOT NULL not null, parent int8, year int4, id int8 not null, primary key (id));
create table medium_position_join (position_id int8, medium_id int8 not null, primary key (medium_id));
create table MediumCount (id int8 not null, primary key (id));
create table MediumPosition (id int8 not null, primary key (id));
create table ralias_sortname_join (sort_name_id int8 not null, ralias_id int8 not null, primary key (ralias_id));
create table recording (gid UUID NOT NULL not null, recording_id int8 not null, name VARCHAR NOT NULL not null, id int8 not null, artist_credit_id int8 not null, recordingLength_id int8, primary key (id));
create table recording_lengthy_base (length int8 not null, id int8 not null, primary key (id));
create table RecordingAlias (begin_date_day int4, begin_date_month int4, begin_date_year int4, end_date_day int4, end_date_month int4, end_date_year int4, locale varchar(1000), recording_alias_id int8 not null, name VARCHAR NOT NULL not null, id int8 not null, recording_id int8 not null, recording_type_id int8 not null, primary key (id));
create table RecordingAliasSortName (id int8 not null, primary key (id));
create table RecordingAliasType (id int8 not null, primary key (id));
create table release (gid VARCHAR(50) NOT NULL not null, name VARCHAR NOT NULL not null, release_id int8 not null, id int8 not null, artist_credit_id int8, language_id int8, release_group_id int8 not null, packaging_id int8, status_id int8, primary key (id));
create table release_alias (name VARCHAR NOT NULL not null, relase_alias_id int8 not null, id int8 not null, release_id int8 not null, type_id int8 not null, primary key (id));
create table release_alias_begin_date_join (date_id int8, release_alias_id int8 not null, primary key (release_alias_id));
create table release_alias_end_date_join (date_id int8, release_alias_id int8 not null, primary key (release_alias_id));
create table release_alias_locale_join (locale_id int8, release_alias_id int8 not null, primary key (release_alias_id));
create table release_alias_sortname_join (sortname_id int8, release_alias_id int8 not null, primary key (release_alias_id));
create table release_barcode_join (barcode_id int8, release_id int8 not null, primary key (release_id));
create table release_comment_join (barcode_id int8, release_id int8 not null, primary key (release_id));
create table release_group (gid UUID NOT NULL not null, name VARCHAR NOT NULL not null, release_group_id int8 not null, id int8 not null, artist_credit_id int8 not null, type_id int8, primary key (id));
create table release_group_primary_type (gid varchar(50) not null, name varchar(255) not null, id int8 not null, primary key (id));
create table release_packaging (gid UUID NOT NULL not null, name VARCHAR NOT NULL not null, release_packaging_id int8 not null, id int8 not null, primary key (id));
create table release_status (gid UUID NOT NULL not null, name VARCHAR NOT NULL not null, release_status_id int8 not null, id int8 not null, primary key (id));
create table ReleaseAliasBeginDate (id int8 not null, primary key (id));
create table ReleaseAliasEndDate (id int8 not null, primary key (id));
create table ReleaseAliasLocale (id int8 not null, primary key (id));
create table ReleaseAliasSortName (id int8 not null, primary key (id));
create table ReleaseAliasType (id int8 not null, primary key (id));
create table ReleaseBarCode (id int8 not null, primary key (id));
create table ReleaseComment (comment VARCHAR NOT NULL not null, id int8 not null, primary key (id));
create table sequence_long_base (id int8 not null, primary key (id));
create table sort_name_base (sort_name VARCHAR NOT NULL not null, id int8 not null, primary key (id));
create table string_number_base (number VARCHAR NOT NULL, id int8 not null, primary key (id));
create table track (track_id int8, id int8 not null, artistCredit_id int8 not null, medium_id int8 not null, recordingId int8 not null, primary key (id));
create table track_length_join (length_id int8 not null, track_id int8 not null, primary key (track_id));
create table track_number_join (number_id int8 not null, track_id int8 not null, primary key (track_id));
create table track_position_join (position_id int8, track_id int8 not null, primary key (track_id));
create table TrackLength (length int8 not null, id int8 not null, track_id int8 not null, primary key (id));
create table TrackNumber (id int8 not null, primary key (id));
create table TrackPosition (id int8 not null, primary key (id));
alter table area add constraint uk_area_id unique (area_id);
alter table artist add constraint uk_artist_id unique (artist_id);
alter table artist_alias add constraint uk_artist_alias_id unique (artist_alias_id);
alter table artist_alias_sortname_join add constraint UK_pj758kjmvfi4w6iylmqqyf87y unique (sort_name_id);
alter table artist_credit add constraint uk_artist_credit_id unique (artist_credit_id);
alter table artist_credit_count_join add constraint UK_b1e6iatyc0a9ebcsnt02cmy6v unique (artist_count_id);
alter table artist_ref_count_join add constraint UK_cjp45m8pm8reo0nj52n626alp unique (artist_refcount_id);
alter table artist_sortname_join add constraint UK_tcbnii28trkvjw0for5lvukji unique (sort_name_id);
create index bar_code_idx on bar_code_base (bar_code);
create index base_type_id_idx on base_type (type_id);
create index begin_date_year_idx on begin_dates (year);
create index description_table_idx on description_base (description);
create index end_date_year_idx on end_dates (end_year);
alter table instrument add constraint uk_instrument_id unique (instrument_id);
alter table language add constraint uk_language_id unique (language_id);
create index locale_table_idx on locale_base (locale);
create index long_count_field_idx on long_count_base (count);
create index long_position_position_idx on long_position_base (position);
alter table Medium add constraint uk_medium_id unique (medium_id);
alter table medium_count_join add constraint UK_t1dpi5c3lb4chf72qb1x0ql3e unique (count_id);
alter table ralias_sortname_join add constraint UK_tanxi0a8ud0dsd29ibeg741i unique (sort_name_id);
alter table recording add constraint uk_recording_id unique (recording_id);
create index long_length_table_idx on recording_lengthy_base (length);
alter table release add constraint uk_release_id unique (release_id);
alter table release_group add constraint uk_release_group_id unique (release_group_id);
alter table release_packaging add constraint uk_release_pack_id unique (release_packaging_id);
alter table release_status add constraint uk_release_status_id unique (release_status_id);
create index sort_name_idx on sort_name_base (sort_name);
create index string_number_number_idx on string_number_base (number);
alter table track_length_join add constraint UK_scyko933yrk07ctctpybraddq unique (length_id);
alter table track_number_join add constraint UK_fnkrp16hmvam21nqghfptddt5 unique (number_id);
alter table TrackLength add constraint UK_absbl72sc7dx3ole44pjeg7ny unique (track_id);
alter table area add constraint FKmf8c4r1f4eevjgm2dokccm1lo foreign key (areaType_id) references AreaType;
alter table area add constraint FKg4s9w75yc9sm9cpbeal5jnjh5 foreign key (id) references sequence_long_base;
alter table area_begin_date_join add constraint FK34yg35u3w51i3te900qh3a0ty foreign key (date_id) references AreaBeginDate;
alter table area_begin_date_join add constraint FKhxwhfi68h7pm6nw99q60kulwx foreign key (area_id) references area;
alter table area_comment_join add constraint FKa87l53y4i7o3ifexsbvp5a299 foreign key (comment_id) references AreaComment;
alter table area_comment_join add constraint FKifb9goxbil6mp8qnlc652fg1o foreign key (area_id) references area;
alter table area_end_date_join add constraint FKmu5tvjjbcs2n96c39l29lhtxo foreign key (date_id) references AreaEndDate;
alter table area_end_date_join add constraint FK2to1rsv7q6jo3oh51adue24sq foreign key (area_id) references area;
alter table AreaBeginDate add constraint FK93fdy15xd9s5fdl6ghlo66rt2 foreign key (id) references begin_dates;
alter table AreaComment add constraint FK76dg0oot31c6y2q449b7i52l9 foreign key (id) references sequence_long_base;
alter table AreaEndDate add constraint FKs8dfx43jt72485pe8h1x9mg6w foreign key (id) references end_dates;
alter table AreaType add constraint FKr4535o1vubuc9s4gdtcpljl3r foreign key (id) references base_type;
alter table artist add constraint FK24ota6aq5ey64f1cirtdrkgw5 foreign key (artistType_id) references ArtistType;
alter table artist add constraint FKc4aiyelw41cd3v4yoybutmyb foreign key (id) references sequence_long_base;
alter table artist_alias add constraint FKqomvlfeckwx3upws72qe9x1nt foreign key (id) references sequence_long_base;
alter table artist_alias_begin_date_join add constraint FKfrxbtcqf4kqq1alefmnhe05sf foreign key (date_id) references ArtistAliasBeginDate;
alter table artist_alias_begin_date_join add constraint FKpks05cos5wm1vmqr2pyjt97sf foreign key (artist_alias_id) references artist_alias;
alter table artist_alias_end_date_join add constraint FKk3wtgwoev9350nh1wo48l5svp foreign key (date_id) references ArtistAliasEndDate;
alter table artist_alias_end_date_join add constraint FKmug4f2wcro7htgp48a4iwycu8 foreign key (artist_alias_id) references artist_alias;
alter table artist_alias_join add constraint FKgdwpq8ctk6a39mvnvsvo2fc5i foreign key (artist_alias_id) references artist;
alter table artist_alias_join add constraint FK48r5krvgiw047ms72erpsxkju foreign key (artist_id) references artist_alias;
alter table artist_alias_locale_join add constraint FK6cwyoivu66wdolbhtmoy29lss foreign key (locale_id) references ArtistAliasLocale;
alter table artist_alias_locale_join add constraint FKtle5378eltgllln6n2jimbrrd foreign key (artist_alias_id) references artist_alias;
alter table artist_alias_sortname_join add constraint FK1427awkpfiqk6qkm86t1bocm7 foreign key (sort_name_id) references ArtistAliasSortName;
alter table artist_alias_sortname_join add constraint FKaljjsn3xplko82gxwoyvnhhdg foreign key (artist_alias_id) references artist_alias;
alter table artist_alias_type_join add constraint FK61kxfefqhh2u14nev0mq7e2rl foreign key (alias_type_id) references ArtistAliasType;
alter table artist_alias_type_join add constraint FKp27ue68w73pcnl22m8cmvopjk foreign key (artist_alias_id) references artist_alias;
alter table artist_area_join add constraint FK5v2pynxsg5phk81a9a888f8p6 foreign key (area_id) references area;
alter table artist_area_join add constraint FKjwws3jf2jrltwhscmyr4c2c8d foreign key (artist_id) references artist;
alter table artist_begin_area_join add constraint FKlib6g7ixl78iper28hwyp4b6j foreign key (area_id) references area;
alter table artist_begin_area_join add constraint FKdsqb47nv5mkfer33g0uwjxrg3 foreign key (artist_id) references artist;
alter table artist_begin_date_join add constraint FK734d3ak1qvt1auqrq88gsjsjh foreign key (date_id) references ArtistBeginDate;
alter table artist_begin_date_join add constraint FK9ep35foih1qqx5cvjt9lwa1gy foreign key (artist_id) references artist;
alter table artist_comment_join add constraint FK5sh23cs6j9tv0i8yta3nldxpw foreign key (comment_id) references ArtistComment;
alter table artist_comment_join add constraint FKtqvsp3t5j61w068n51iowndiw foreign key (artist_id) references artist;
alter table artist_credit add constraint FKfflln8ulxrs8vyupvx9umfmd4 foreign key (id) references sequence_long_base;
alter table artist_credit_count_join add constraint FKll8hqnpmjv9yq8y4mtsrik05e foreign key (artist_count_id) references ArtistCreditCount;
alter table artist_credit_count_join add constraint FK9pp1ahlexg9hgppy4p3eeit7y foreign key (artist_credit_id) references artist_credit;
alter table artist_credit_name_position_join add constraint FK16bpho77fdbmp3p1n9cuodgy3 foreign key (position_id) references ArtistCreditedNamePosition;
alter table artist_credit_name_position_join add constraint FK92reeafxnwbh56ucuujcip32m foreign key (artist_credit_name) references ArtistCreditedName;
alter table artist_credit_name_rel add constraint FKn3g0jyj028jrwt6ckpuvk1y5j foreign key (artist_name_id) references artist;
alter table artist_credit_name_rel add constraint FK44fgvkqt29bcp2ai5oewfl4p1 foreign key (artist_credit_id) references artist_credit;
alter table artist_end_area_join add constraint FKdq9xln44yycsfyifwjhpojy5 foreign key (area_id) references area;
alter table artist_end_area_join add constraint FKqpmbpcxmsx9horym1828a6nhm foreign key (artist_id) references artist;
alter table artist_end_date_join add constraint FK6117ksvtk131rbleg0f2hlfy3 foreign key (date_id) references ArtistEndDate;
alter table artist_end_date_join add constraint FK9xnh1e0g4gykda5onwvpl96w0 foreign key (artist_id) references artist;
alter table artist_gender_join add constraint FKebm6l5hwjy51kowqos8nevry1 foreign key (gender_id) references Gender;
alter table artist_gender_join add constraint FKhmr79ir0dvn6l6g2k4asxe9rh foreign key (artist_id) references artist;
alter table artist_ref_count_join add constraint FKikjq6c73b5kk6mru5xo2vuafu foreign key (artist_refcount_id) references ArtistCreditRefCount;
alter table artist_ref_count_join add constraint FKsug54x25nuu957axm0c4obs0u foreign key (artist_credit_id) references artist_credit;
alter table artist_sortname_join add constraint FKivpnvwenmeclwniviirs2twnn foreign key (sort_name_id) references ArtistSortName;
alter table artist_sortname_join add constraint FK6q14bw7t5sywulcx7pcu6bjcs foreign key (artist_id) references artist;
alter table ArtistAliasBeginDate add constraint FKkpu3cl6lb3qhbkwxf48xel3bv foreign key (id) references begin_dates;
alter table ArtistAliasEndDate add constraint FKsr6vn86kxb85r15aav3oj0hae foreign key (id) references end_dates;
alter table ArtistAliasLocale add constraint FKgqb5ylpnr0jic0pwqeti6qqk3 foreign key (id) references locale_base;
alter table ArtistAliasSortName add constraint FKq30q19wdrp3bc67h8v00u8qq2 foreign key (id) references sort_name_base;
alter table ArtistAliasType add constraint FKalono78x6746rw2wcmg6t46k2 foreign key (id) references base_type;
alter table ArtistBeginDate add constraint FKdhcho5e2j23qhnig8tjjk6tvq foreign key (id) references begin_dates;
alter table ArtistComment add constraint FKopydxedgw5curkv23kbiiy6gs foreign key (id) references sequence_long_base;
alter table ArtistCreditCount add constraint FK4fcx6w5gsbalh45mm57vfkww4 foreign key (id) references long_count_base;
alter table ArtistCreditedName add constraint FKs9buctrdceu49ll5hbk8wddwl foreign key (artistid) references artist;
alter table ArtistCreditedName add constraint FKamn4sghaewa0iy4b6y0ysdyej foreign key (artist_credit_id) references artist_credit;
alter table ArtistCreditedName add constraint FK7k8tot5t7td9gsrb75yw02tvq foreign key (id) references sequence_long_base;
alter table ArtistCreditedNamePosition add constraint FKryaoc0uxwu1ydumm0n34agagm foreign key (id) references long_position_base;
alter table ArtistCreditRefCount add constraint FKcgtbya3siypr17q4xkwovosb foreign key (id) references long_count_base;
alter table ArtistEndDate add constraint FKi72cqk5nmh1e13tft96alueph foreign key (id) references end_dates;
alter table ArtistSortName add constraint FKr6h4vmhne787iw57efomuj22h foreign key (id) references sort_name_base;
alter table ArtistType add constraint FKedp2kuqtci95iw7guvt968j3c foreign key (id) references base_type;
alter table bar_code_base add constraint FKhhx8v91oj83kfx7w9oyljt1f5 foreign key (id) references sequence_long_base;
alter table base_type add constraint FKs2s4pysnaoq2teuob77eobxrx foreign key (id) references sequence_long_base;
alter table base_type_description_join add constraint FK9c72onvhw3id5skunpdd7a64w foreign key (description_id) references BaseTypeDescription;
alter table base_type_description_join add constraint FK44wmj6925gqqena2mnr2d3c0e foreign key (base_type_id) references base_type;
alter table BaseTypeDescription add constraint FKe7v2t9ya2c09y99xsu5gjsuab foreign key (id) references description_base;
alter table begin_dates add constraint FKja8e2ej2lnti4qm2dl30iwlpd foreign key (id) references sequence_long_base;
alter table description_base add constraint FKkmt5gix2a01tduqcitxd1jlgh foreign key (id) references sequence_long_base;
alter table end_dates add constraint FKrsn1s6iuijvxbgnxrx7ic479s foreign key (id) references sequence_long_base;
alter table Gender add constraint FK5lmp1q9iu740xud1gxangsrg8 foreign key (id) references base_type;
alter table instrument add constraint FKs5sx72k7r98e09oq8km8nx858 foreign key (instrumentType_id) references InstrumentType;
alter table instrument add constraint FK5i1eljc6mh6kyblksvwyhg9ho foreign key (id) references sequence_long_base;
alter table instrument_comment_join add constraint FKijmebsqk04tiq99kvcdq1y9m3 foreign key (comment_id) references InstrumentComment;
alter table instrument_comment_join add constraint FK8dawlfgg0km0ofqhs7k8i10rb foreign key (instrument_id) references instrument;
alter table instrument_description_join add constraint FK3n5n3wti46tnak8d26jvwa08l foreign key (description_id) references InstrumentDescription;
alter table instrument_description_join add constraint FKkvq50u8tmgigintxinspal853 foreign key (instrument_id) references instrument;
alter table InstrumentComment add constraint FKiy47g2k05xl6rwystlirbtjhy foreign key (id) references sequence_long_base;
alter table InstrumentDescription add constraint FKp8621h8x4grikq4tuagdem2as foreign key (id) references description_base;
alter table InstrumentType add constraint FKifirbapdceb91xqv0vibhd3vq foreign key (id) references base_type;
alter table isrc add constraint FKppm8vkrurdw6t5ejhdhmxx0ob foreign key (id) references sequence_long_base;
alter table label add constraint FKqmabfbh4bw9uowqp733r6f3rb foreign key (area_id) references area;
alter table label add constraint FK32s2u14tq9rpc3hc2o8trj68u foreign key (label_begin_date_id) references LabelBeginDate;
alter table label add constraint FKqhspn2wghvto5ndp6etaf9tkp foreign key (label_end_date_id) references LabelEndDate;
alter table label add constraint FKabu2ij1wpgjm0s1bdhrmitw49 foreign key (labelType_id) references LabelType;
alter table label add constraint FK1ofufh6tyya5sp2tehipsgngc foreign key (id) references sort_name_base;
alter table LabelBeginDate add constraint FK82yp05gb2hu0h6xpycylbfifk foreign key (id) references begin_dates;
alter table LabelEndDate add constraint FKho5fo55sy9maaakb4qvper8sa foreign key (id) references end_dates;
alter table LabelType add constraint FKbptimehjxmrny3x9vol04fitl foreign key (id) references base_type;
alter table language add constraint FKp5l03j091bbc9qevkarfl5q8t foreign key (id) references sequence_long_base;
alter table locale_base add constraint FKtcwrst1n53cpl414t5qkqlstd foreign key (id) references sequence_long_base;
alter table long_count_base add constraint FKpijf7i99hla06412ha1m4vh3b foreign key (id) references sequence_long_base;
alter table long_number_base add constraint FKdbuxo0xqdottj88ys6puascst foreign key (id) references sequence_long_base;
alter table long_position_base add constraint FK6cofolrx7ctukjwhkeathrswb foreign key (id) references sequence_long_base;
alter table LongIdSortName add constraint FK2edrvfljw3hpy3ksdrx4vwiy8 foreign key (id) references sequence_long_base;
alter table Medium add constraint FK88xlaa32r1xxpm9vykwh3675e foreign key (medium_format_id) references medium_format;
alter table Medium add constraint FKkhs58g5n5bnyqr14hklutr1jc foreign key (release_id) references release;
alter table Medium add constraint FK9mefacjneess8m068pog62gwq foreign key (id) references sequence_long_base;
alter table medium_count_join add constraint FK77hdvmhh6o8a14ivyww670jpy foreign key (count_id) references MediumCount;
alter table medium_count_join add constraint FK5gnqnfnwrqj7uuio47742wsqt foreign key (medium_id) references Medium;
alter table medium_format add constraint FKl4ya85pdfq3fhpt8bbhw63ee6 foreign key (id) references sequence_long_base;
alter table medium_position_join add constraint FKob85k1mnbwwufgik878sx4w66 foreign key (position_id) references MediumPosition;
alter table medium_position_join add constraint FKqpxdjbhc5y2t6sb9g0d6sswg2 foreign key (medium_id) references Medium;
alter table MediumCount add constraint FKd0gnm2k22chswino0bosjy1vr foreign key (id) references long_count_base;
alter table MediumPosition add constraint FK6ojs0jgh73pbck6q0e2jw4kq2 foreign key (id) references long_position_base;
alter table ralias_sortname_join add constraint FK4g2yi7hb987xg7kqt16acn3ba foreign key (sort_name_id) references RecordingAliasSortName;
alter table ralias_sortname_join add constraint FKjhkstwd4cbqek9d5hqh5p02y9 foreign key (ralias_id) references RecordingAlias;
alter table recording add constraint FKwhnq53y2fteeexfpar6558ut foreign key (artist_credit_id) references artist_credit;
alter table recording add constraint FKp6h8vblmfp7fbvy7t07unr441 foreign key (recordingLength_id) references recording_lengthy_base;
alter table recording add constraint FK7eub4y9nm9wb4rc8sr7xdeu1v foreign key (id) references sequence_long_base;
alter table recording_lengthy_base add constraint FKtd3xp568ud34ghhdueiu71cib foreign key (id) references sequence_long_base;
alter table RecordingAlias add constraint FKe9vlke7fpdlgg80ax13gp7klf foreign key (recording_id) references recording;
alter table RecordingAlias add constraint FKlsqjw4u8lgldn0ush7t1vmk7i foreign key (recording_type_id) references RecordingAliasType;
alter table RecordingAlias add constraint FKn2hv2nwl1n7fo7p09k0ab0ked foreign key (id) references LongIdSortName;
alter table RecordingAliasSortName add constraint FK9b0wp3o32ly0iv9km0gckr9vp foreign key (id) references sort_name_base;
alter table RecordingAliasType add constraint FKgi0bcnu4b95rbnovoat59xglc foreign key (id) references base_type;
alter table release add constraint FKrjxedvgqei5yg4s02538jeu55 foreign key (artist_credit_id) references artist_credit;
alter table release add constraint FKne7hflamxfjrj3nwcp7rmyqv1 foreign key (language_id) references language;
alter table release add constraint FKrdc218r3ti4ecmiwm79k8nrm5 foreign key (release_group_id) references release_group;
alter table release add constraint FKevsgr1j8wgbldkpylyaaruorq foreign key (packaging_id) references release_packaging;
alter table release add constraint FKqrik1raq7rgbf262jxyuy9m5f foreign key (status_id) references release_status;
alter table release add constraint FK79w3845c70qmsug6mp0mstd5f foreign key (id) references sequence_long_base;
alter table release_alias add constraint FK8h783sk9tygkr28w7uaw66hgs foreign key (release_id) references release;
alter table release_alias add constraint FK2t0eyhwmmxyhavv0vfc52tsn0 foreign key (type_id) references ReleaseAliasType;
alter table release_alias add constraint FKedclfy3pdpdgt668m7xvxw1ek foreign key (id) references sequence_long_base;
alter table release_alias_begin_date_join add constraint FKhcwgmy61646i9joaeqrqlxaah foreign key (date_id) references ReleaseAliasBeginDate;
alter table release_alias_begin_date_join add constraint FK8fwb728l8xka6plxvd5ioam9v foreign key (release_alias_id) references release_alias;
alter table release_alias_end_date_join add constraint FKbauhnvej3oh7yvfd47toshpts foreign key (date_id) references ReleaseAliasEndDate;
alter table release_alias_end_date_join add constraint FKh23905t8qli78gtd54skm2k32 foreign key (release_alias_id) references release_alias;
alter table release_alias_locale_join add constraint FK4nb7t5akquuns24nf7ertd9r4 foreign key (locale_id) references ReleaseAliasLocale;
alter table release_alias_locale_join add constraint FKfi3muh2yf67v2e3aavs7o23iy foreign key (release_alias_id) references release_alias;
alter table release_alias_sortname_join add constraint FK3nrqq7d0h9esq8whoas84mpjw foreign key (sortname_id) references ReleaseAliasSortName;
alter table release_alias_sortname_join add constraint FK47aqy9lhj7y73lnygfx8i5wbv foreign key (release_alias_id) references release_alias;
alter table release_barcode_join add constraint FK2xjw2wm9hsulr6nqwjo91anul foreign key (barcode_id) references ReleaseBarCode;
alter table release_barcode_join add constraint FK1ohu8rea5r4bflvjfo52wuiak foreign key (release_id) references release;
alter table release_comment_join add constraint FKpum7hvj1bfx68kckhqnk068ow foreign key (barcode_id) references ReleaseComment;
alter table release_comment_join add constraint FKaso5up8i49s07ak8mqb1w13bo foreign key (release_id) references release;
alter table release_group add constraint FKjhugaa7xl4008xcscrx21v8iw foreign key (artist_credit_id) references artist_credit;
alter table release_group add constraint FKngm4tpa9mbjtosws33t7pryhy foreign key (type_id) references release_group_primary_type;
alter table release_group add constraint FKn818fgbq30ula023ntkjlhtrg foreign key (id) references sequence_long_base;
alter table release_group_primary_type add constraint FK8qcxtf31eji53rpv5nm7gcrag foreign key (id) references sequence_long_base;
alter table release_packaging add constraint FKm9v158rs5sbggn402pw7mxe0i foreign key (id) references sequence_long_base;
alter table release_status add constraint FKken4apjgithfet1mxm5p25hwo foreign key (id) references sequence_long_base;
alter table ReleaseAliasBeginDate add constraint FKc27o463sr9lrvjg2064eyyh07 foreign key (id) references begin_dates;
alter table ReleaseAliasEndDate add constraint FKtp0nxbkxmcakrk6609bh5xhod foreign key (id) references end_dates;
alter table ReleaseAliasLocale add constraint FKjrk0l6s7uhiupa4m3cg10obqd foreign key (id) references locale_base;
alter table ReleaseAliasSortName add constraint FKlg8kty5f3mtu8ndql7dia2t1y foreign key (id) references sort_name_base;
alter table ReleaseAliasType add constraint FKpn1yuiiipoklqkwsfv8ysdptd foreign key (id) references base_type;
alter table ReleaseBarCode add constraint FK73mj9tgmcs92sodeirwbawxy4 foreign key (id) references bar_code_base;
alter table ReleaseComment add constraint FK8jwsa1skg4wwx0rpq66um073p foreign key (id) references sequence_long_base;
alter table sort_name_base add constraint FKjayydqd9c78b3fwdhx9u1anqq foreign key (id) references sequence_long_base;
alter table string_number_base add constraint FKa33iygo9ngbkeblg6klicksli foreign key (id) references sequence_long_base;
alter table track add constraint FKir00mv1qv09apd7emsparvh9n foreign key (artistCredit_id) references artist_credit;
alter table track add constraint FKqxta8bmt9en1errp76u3m2oau foreign key (medium_id) references Medium;
alter table track add constraint FKcbmq3cor34r184u84jtfhiw1r foreign key (recordingId) references recording;
alter table track add constraint FKi5xi8fmh4hvx9g7mec4kpjyfd foreign key (id) references sequence_long_base;
alter table track_length_join add constraint FKclg3rc7jynq5lx20ojjqdlj3 foreign key (length_id) references TrackLength;
alter table track_length_join add constraint FK3aj1gvdufrwb9snay9cy91t53 foreign key (track_id) references track;
alter table track_number_join add constraint FKnw2r6kjxj6jnlgrm9pblkp97s foreign key (number_id) references TrackNumber;
alter table track_number_join add constraint FKjfj36ofn0l3no50vutks0ha5x foreign key (track_id) references track;
alter table track_position_join add constraint FKm2ot2r239l67s7gc9kt2qxn8f foreign key (position_id) references TrackPosition;
alter table track_position_join add constraint FKptd32j597vii6l8900688trkt foreign key (track_id) references track;
alter table TrackLength add constraint FKcv70am9lwgev48d507ol84qvs foreign key (track_id) references track;
alter table TrackLength add constraint FKlyxe68qrwbgkbh7ke6c4ci4dr foreign key (id) references sequence_long_base;
alter table TrackNumber add constraint FKqp6ra6uo50o3v0uepfgb5ruii foreign key (id) references string_number_base;
alter table TrackPosition add constraint FKjt0uy4r163atthuevkgyne3yk foreign key (id) references long_position_base;
