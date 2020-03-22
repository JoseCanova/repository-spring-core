create sequence artist_credit_name_id_seq start 1 increment 50
create sequence isrc_id_seq start 1 increment 50
create sequence sequence_id_seq start 1 increment 50
create table area (area_id int8 not null, id int8 not null, type_id int8 not null, primary key (id))
create table area_begin_date_join (date_id int8, area_id int8 not null, primary key (area_id))
create table area_comment_join (comment_id int8, area_id int8 not null, primary key (area_id))
create table area_end_date_join (date_id int8, area_id int8 not null, primary key (area_id))
create table AreaType (id int8 not null, primary key (id))
create table artist (artist_id int8 not null, id int8 not null, type_id int8 not null, primary key (id))
create table artist_alias (artist_alias_id int8 not null, id int8 not null, primary key (id))
create table artist_alias_begin_date_join (date_id int8, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_alias_end_date_join (date_id int8, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_alias_join (artist_id int8 not null, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_alias_locale_join (locale_id int8, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_alias_sortname_join (sort_name_id int8 not null, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_alias_type_join (alias_type_id int8 not null, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_area_join (area_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_begin_area_join (area_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_begin_date_join (date_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_comment_join (comment_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_credit (artist_credit_id int8 not null, id int8 not null, primary key (id))
create table artist_credit_count_join (artist_count_id int8 not null, artist_credit_id int8 not null, primary key (artist_credit_id))
create table artist_credit_name (id int8 not null, artist_credit_name_join_prase varchar(2000), ARTIST_NAME varchar(1000), ARTIST_CREDIT_NAME_POSITION int8, artistid int8, artist_credit_id int8, primary key (id))
create table artist_credit_name_rel (artist_credit_id int8 not null, artist_name_id int8 not null)
create table artist_end_area_join (area_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_end_date_join (date_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_gender_join (gender_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_ref_count_join (artist_refcount_id int8 not null, artist_credit_id int8 not null, primary key (artist_credit_id))
create table artist_sortname_join (sort_name_id int8 not null, artist_id int8 not null, primary key (artist_id))
create table ArtistAliasType (id int8 not null, primary key (id))
create table ArtistType (id int8 not null, primary key (id))
create table bar_code_base (table_id VARCHAR NOT NULL not null, id int8 not null, bar_code varchar(255) not null, primary key (id))
create table base_type (childOrder int8, parent int8, type_id int8, id int8 not null, primary key (id))
create table base_type_description_join (description_id int8, base_type_id int8 not null, primary key (base_type_id))
create table comment_base (table_id VARCHAR NOT NULL not null, id int8 not null, comment VARCHAR NOT NULL not null, primary key (id))
create table composite_dates (table_id VARCHAR NOT NULL not null, id int8 not null, day int4, month int4, year int4 not null, primary key (id))
create table description_base (table_id VARCHAR NOT NULL not null, id int8 not null, description VARCHAR NOT NULL not null, primary key (id))
create table description_comment_join (description_id int8, instrument_id int8 not null, primary key (instrument_id))
create table Gender (id int8 not null, primary key (id))
create table instrument (instrument_id int8 not null, id int8 not null, type_id int8 not null, primary key (id))
create table instrument_comment_join (comment_id int8, instrument_id int8 not null, primary key (instrument_id))
create table InstrumentType (id int8 not null, primary key (id))
create table InstrumentType_instrument (InstrumentType_id int8 not null, instruments_id int8 not null, primary key (InstrumentType_id, instruments_id))
create table isrc (id int8 not null, ISRC varchar(255), ISRC_ID int8, RECORDING_ID int8, SOURCE varchar(255), primary key (id))
create table label (id int8 not null, beginDateDay varchar(255), beginDateMonth varchar(255), beginDateYear varchar(255), country varchar(255), endDateDay varchar(255), endDateMonth varchar(255), endDateYear varchar(255), gid varchar(255), ipiCode varchar(255), labelCode varchar(255), labelId int8, name int8, sortName int8, type varchar(255), primary key (id))
create table language (frequency int8, isoCode1 varchar(2), isoCode2b varchar(3), isoCode2t varchar(3), isoCode3 varchar(3), language_id int8, id int8 not null, primary key (id))
create table locale_base (table_id VARCHAR NOT NULL not null, id int8 not null, locale VARCHAR NOT NULL, primary key (id))
create table long_count_base (table_id VARCHAR NOT NULL not null, id int8 not null, count int8 not null, primary key (id))
create table long_id_gid_name (gid varchar(50) not null, id int8 not null, primary key (id))
create table long_id_name (id int8 not null, name VARCHAR NOT NULL not null, primary key (id))
create table long_lengthy_base (table_id VARCHAR NOT NULL not null, id int8 not null, length int8 not null, track_id int8 not null, primary key (id))
create table long_number_base (table_id VARCHAR NOT NULL not null, id int8 not null, number int8 not null, primary key (id))
create table long_position_base (table_id VARCHAR NOT NULL not null, id int8 not null, position int8 not null, primary key (id))
create table Medium (medium_id int8, id int8 not null, medium_format_id int8 not null, release_id int8 not null, primary key (id))
create table medium_count_join (position_id int8 not null, medium_id int8 not null, primary key (medium_id))
create table medium_format (id int8 not null, description varchar(4000), gid varchar(50) not null, hasDiscId varchar(6) not null, name varchar(100) not null, parent int8, year int4, primary key (id))
create table medium_position_join (position_id int8 not null, medium_id int8 not null, primary key (medium_id))
create table recording (recording_id int8 not null, id int8 not null, artist_credit_id int8 not null, recordingLenght_id int8, primary key (id))
create table recording_alias (id int8 not null, begin_date_day int4, begin_date_month int4, begin_date_year int4, end_date_day int4, end_date_month int4, end_date_year int4, locale varchar(1000), name varchar(1000) not null, sortName varchar(1000) not null, recording_id int8 not null, recording_type_id int8, primary key (id))
create table RecordingAliasType (id int8 not null, primary key (id))
create table release (release_id int8 not null, id int8 not null, artist_credit_id int8, language_id int8, packaging_id int8, release_group_id int8 not null, status_id int8, primary key (id))
create table release_alias (relase_alias_id int8 not null, id int8 not null, release_id int8 not null, type_id int8 not null, primary key (id))
create table release_alias_begin_date_join (date_id int8, release_alias_id int8 not null, primary key (release_alias_id))
create table release_alias_end_date_join (date_id int8, release_alias_id int8 not null, primary key (release_alias_id))
create table release_alias_locale_join (locale_id int8, release_alias_id int8 not null, primary key (release_alias_id))
create table release_alias_sortname_join (sortname_id int8 not null, release_alias_id int8 not null, primary key (release_alias_id))
create table release_barcode_join (barcode_id int8, release_id int8 not null, primary key (release_id))
create table release_comment_join (barcode_id int8, release_id int8 not null, primary key (release_id))
create table release_group (release_group_id int8 not null, id int8 not null, artist_credit_id int8 not null, type_id int8, primary key (id))
create table release_group_primary_type (id int8 not null, gid varchar(50) not null, name varchar(255) not null, primary key (id))
create table release_packaging (release_packaging_id int8 not null, id int8 not null, primary key (id))
create table release_status (release_status_id int8 not null, id int8 not null, primary key (id))
create table ReleaseAliasType (id int8 not null, primary key (id))
create table sort_name_base (table_id VARCHAR NOT NULL not null, id int8 not null, sort_name VARCHAR NOT NULL not null, primary key (id))
create table string_number_base (table_id VARCHAR NOT NULL not null, id int8 not null, number VARCHAR NOT NULL, primary key (id))
create table track (track_id int8, id int8 not null, artistCredit_id int8 not null, medium_id int8 not null, recordingId int8 not null, primary key (id))
create table track_length_join (length_id int8 not null, track_id int8 not null, primary key (track_id))
create table track_number_join (number_id int8 not null, track_id int8 not null, primary key (track_id))
create table track_position_join (position_id int8, track_id int8 not null, primary key (track_id))
alter table area add constraint uk_area_id unique (area_id)
alter table artist add constraint uk_artist_id unique (artist_id)
alter table artist_alias add constraint uk_artist_alias_id unique (artist_alias_id)
alter table artist_alias_sortname_join add constraint UK_pj758kjmvfi4w6iylmqqyf87y unique (sort_name_id)
alter table artist_credit add constraint uk_artist_credit_id unique (artist_credit_id)
alter table artist_credit_count_join add constraint UK_b1e6iatyc0a9ebcsnt02cmy6v unique (artist_count_id)
alter table artist_ref_count_join add constraint UK_cjp45m8pm8reo0nj52n626alp unique (artist_refcount_id)
alter table artist_sortname_join add constraint UK_tcbnii28trkvjw0for5lvukji unique (sort_name_id)
create index bar_code_idx on bar_code_base (bar_code)
create index base_type_id_idx on base_type (type_id)
create index comment_table_idx on comment_base (comment)
create index date_year_idx on composite_dates (year)
create index description_table_idx on description_base (description)
create index description_table_id_idx on description_base (table_id)
alter table instrument add constraint uk_instrument_id unique (instrument_id)
alter table InstrumentType_instrument add constraint UK_dgf73kj1tm86puuhtoatiqqkk unique (instruments_id)
alter table language add constraint uk_language_id unique (language_id)
create index locale_table_idx on locale_base (locale)
create index long_count_field_idx on long_count_base (count)
alter table long_id_gid_name add constraint uk_id_gid_name unique (gid)
create index name_idx on long_id_name (name)
create index long_length_table_idx on long_lengthy_base (length)
alter table long_lengthy_base add constraint UK_ns2jmpxdohnw34m6eqpj9q5ny unique (track_id)
create index long_position_position_idx on long_position_base (position)
create index long_position_table_idx on long_position_base (table_id)
alter table Medium add constraint uk_medium_id unique (medium_id)
alter table medium_count_join add constraint UK_r7raky87e1ui5csktub3ecdco unique (position_id)
alter table recording add constraint uk_recording_id unique (recording_id)
alter table release add constraint uk_release_id unique (release_id)
alter table release_group add constraint uk_release_group_id unique (release_group_id)
alter table release_packaging add constraint uk_release_pack_id unique (release_packaging_id)
alter table release_status add constraint uk_release_status_id unique (release_status_id)
create index sort_name_idx on sort_name_base (sort_name)
create index string_number_number_idx on string_number_base (number)
alter table track_length_join add constraint UK_scyko933yrk07ctctpybraddq unique (length_id)
alter table track_number_join add constraint UK_fnkrp16hmvam21nqghfptddt5 unique (number_id)
alter table area add constraint FK36ijraeico3lyy6mtk1sqpi8t foreign key (type_id) references AreaType
alter table area add constraint FK1bgs2s8cvrbmh8sn23l16vj0q foreign key (id) references long_id_gid_name
alter table area_begin_date_join add constraint FKp4uqnvhavqhyyatk5dfrieo6j foreign key (date_id) references composite_dates
alter table area_begin_date_join add constraint FKhxwhfi68h7pm6nw99q60kulwx foreign key (area_id) references area
alter table area_comment_join add constraint FKht1gjphb8hqa33q1rjnh1uyy4 foreign key (comment_id) references comment_base
alter table area_comment_join add constraint FKifb9goxbil6mp8qnlc652fg1o foreign key (area_id) references area
alter table area_end_date_join add constraint FKs9pkgex6hjbjjvognm0jvyve foreign key (date_id) references composite_dates
alter table area_end_date_join add constraint FK2to1rsv7q6jo3oh51adue24sq foreign key (area_id) references area
alter table AreaType add constraint FKr4535o1vubuc9s4gdtcpljl3r foreign key (id) references base_type
alter table artist add constraint FKjft6k91ihgppugrvdu4rb3qe2 foreign key (type_id) references ArtistType
alter table artist add constraint FK5j7iyhq5y0u5bkj6c8i5b3idg foreign key (id) references long_id_gid_name
alter table artist_alias add constraint FKdfpdahx1espsoktxbv5toco1b foreign key (id) references long_id_name
alter table artist_alias_begin_date_join add constraint FKr5yh1fq8o1vaaiaosk1okb3ab foreign key (date_id) references composite_dates
alter table artist_alias_begin_date_join add constraint FKpks05cos5wm1vmqr2pyjt97sf foreign key (artist_alias_id) references artist_alias
alter table artist_alias_end_date_join add constraint FKm5c36tqplh8bwp1qrw0phxv0i foreign key (date_id) references composite_dates
alter table artist_alias_end_date_join add constraint FKmug4f2wcro7htgp48a4iwycu8 foreign key (artist_alias_id) references artist_alias
alter table artist_alias_join add constraint FKgcd77qcx31282qmxeaypm2wl1 foreign key (artist_id) references artist
alter table artist_alias_join add constraint FKjjqk70jqobun8g8ywh9twu8d2 foreign key (artist_alias_id) references artist_alias
alter table artist_alias_locale_join add constraint FKb6xi7sb9l8h80v4qdb52gewf2 foreign key (locale_id) references locale_base
alter table artist_alias_locale_join add constraint FKtle5378eltgllln6n2jimbrrd foreign key (artist_alias_id) references artist_alias
alter table artist_alias_sortname_join add constraint FKtnhfe96x3gvwohh93u88harun foreign key (sort_name_id) references sort_name_base
alter table artist_alias_sortname_join add constraint FKaljjsn3xplko82gxwoyvnhhdg foreign key (artist_alias_id) references artist_alias
alter table artist_alias_type_join add constraint FK61kxfefqhh2u14nev0mq7e2rl foreign key (alias_type_id) references ArtistAliasType
alter table artist_alias_type_join add constraint FKp27ue68w73pcnl22m8cmvopjk foreign key (artist_alias_id) references artist_alias
alter table artist_area_join add constraint FK5v2pynxsg5phk81a9a888f8p6 foreign key (area_id) references area
alter table artist_area_join add constraint FKjwws3jf2jrltwhscmyr4c2c8d foreign key (artist_id) references artist
alter table artist_begin_area_join add constraint FKlib6g7ixl78iper28hwyp4b6j foreign key (area_id) references area
alter table artist_begin_area_join add constraint FKdsqb47nv5mkfer33g0uwjxrg3 foreign key (artist_id) references artist
alter table artist_begin_date_join add constraint FKr4e3bw96hbaq4qopldq91owtr foreign key (date_id) references composite_dates
alter table artist_begin_date_join add constraint FK9ep35foih1qqx5cvjt9lwa1gy foreign key (artist_id) references artist
alter table artist_comment_join add constraint FK5ny27souggg2m4ovikg8ii5jn foreign key (comment_id) references comment_base
alter table artist_comment_join add constraint FKtqvsp3t5j61w068n51iowndiw foreign key (artist_id) references artist
alter table artist_credit add constraint FKsfhccwknqcc8klrsbswt1hdsp foreign key (id) references long_id_name
alter table artist_credit_count_join add constraint FKf79dl4q17hswqhv9y4mb65qat foreign key (artist_count_id) references long_count_base
alter table artist_credit_count_join add constraint FK9pp1ahlexg9hgppy4p3eeit7y foreign key (artist_credit_id) references artist_credit
alter table artist_credit_name add constraint FK7js2hdti9oon8qxw0imp1s0ts foreign key (artistid) references artist
alter table artist_credit_name add constraint FKc8rsbsxnwsngtfqha46qhjlfv foreign key (artist_credit_id) references artist_credit
alter table artist_credit_name_rel add constraint FKn3g0jyj028jrwt6ckpuvk1y5j foreign key (artist_name_id) references artist
alter table artist_credit_name_rel add constraint FK44fgvkqt29bcp2ai5oewfl4p1 foreign key (artist_credit_id) references artist_credit
alter table artist_end_area_join add constraint FKdq9xln44yycsfyifwjhpojy5 foreign key (area_id) references area
alter table artist_end_area_join add constraint FKqpmbpcxmsx9horym1828a6nhm foreign key (artist_id) references artist
alter table artist_end_date_join add constraint FK72tv7jmp2dy6ivoegudsuyann foreign key (date_id) references composite_dates
alter table artist_end_date_join add constraint FK9xnh1e0g4gykda5onwvpl96w0 foreign key (artist_id) references artist
alter table artist_gender_join add constraint FKebm6l5hwjy51kowqos8nevry1 foreign key (gender_id) references Gender
alter table artist_gender_join add constraint FKhmr79ir0dvn6l6g2k4asxe9rh foreign key (artist_id) references artist
alter table artist_ref_count_join add constraint FKlb2e7nxkf00c41ynywljxmuiy foreign key (artist_refcount_id) references long_count_base
alter table artist_ref_count_join add constraint FKsug54x25nuu957axm0c4obs0u foreign key (artist_credit_id) references artist_credit
alter table artist_sortname_join add constraint FK56a9947m1dr2c1j6l52plkspq foreign key (sort_name_id) references sort_name_base
alter table artist_sortname_join add constraint FK6q14bw7t5sywulcx7pcu6bjcs foreign key (artist_id) references artist
alter table ArtistAliasType add constraint FKalono78x6746rw2wcmg6t46k2 foreign key (id) references base_type
alter table ArtistType add constraint FKedp2kuqtci95iw7guvt968j3c foreign key (id) references base_type
alter table base_type add constraint FKodn6b3grgeqmw6b2auasx9n4e foreign key (id) references long_id_gid_name
alter table base_type_description_join add constraint FKckvph0x1homuj7hn7wver3f8k foreign key (description_id) references description_base
alter table base_type_description_join add constraint FK44wmj6925gqqena2mnr2d3c0e foreign key (base_type_id) references base_type
alter table description_comment_join add constraint FKgwus7t07ciceehfgxllpj5qbu foreign key (description_id) references description_base
alter table description_comment_join add constraint FK6m3tg6kbamffc1gx8ptughdsg foreign key (instrument_id) references instrument
alter table Gender add constraint FK5lmp1q9iu740xud1gxangsrg8 foreign key (id) references base_type
alter table instrument add constraint FK94bpiu9rbn67ehf5k349ooksf foreign key (type_id) references InstrumentType
alter table instrument add constraint FKj66ki180cgmuttavrsr0etjlw foreign key (id) references long_id_gid_name
alter table instrument_comment_join add constraint FK2jwbt9rug9qg7co42h1n2wrfx foreign key (comment_id) references comment_base
alter table instrument_comment_join add constraint FK8dawlfgg0km0ofqhs7k8i10rb foreign key (instrument_id) references instrument
alter table InstrumentType add constraint FKifirbapdceb91xqv0vibhd3vq foreign key (id) references base_type
alter table InstrumentType_instrument add constraint FKktv0l7uftgcf6ncyjxx7w6rjq foreign key (instruments_id) references instrument
alter table InstrumentType_instrument add constraint FK9kufqmeecpl0ho81sffyn9y8d foreign key (InstrumentType_id) references InstrumentType
alter table language add constraint FKtb4qe7sw6ccf7pwoj11hfslx2 foreign key (id) references long_id_name
alter table long_id_gid_name add constraint FKse71nfag7h861smx8ju4becl9 foreign key (id) references long_id_name
alter table long_lengthy_base add constraint FKeoqyoydaygg3uc0uflpedqpjb foreign key (track_id) references track
alter table Medium add constraint FK88xlaa32r1xxpm9vykwh3675e foreign key (medium_format_id) references medium_format
alter table Medium add constraint FKkhs58g5n5bnyqr14hklutr1jc foreign key (release_id) references release
alter table Medium add constraint FKd29oftthn2kc1h93yvak4o8oh foreign key (id) references long_id_name
alter table medium_count_join add constraint FKnb6vfdqw575q0c53w4c43bae1 foreign key (position_id) references long_count_base
alter table medium_count_join add constraint FK5gnqnfnwrqj7uuio47742wsqt foreign key (medium_id) references Medium
alter table medium_position_join add constraint FK7npic7e0xpkw01dji0v59jimt foreign key (position_id) references long_position_base
alter table medium_position_join add constraint FKqpxdjbhc5y2t6sb9g0d6sswg2 foreign key (medium_id) references Medium
alter table recording add constraint FKwhnq53y2fteeexfpar6558ut foreign key (artist_credit_id) references artist_credit
alter table recording add constraint FKtdh05j1q5nmsl9q7mo77gtssp foreign key (recordingLenght_id) references long_lengthy_base
alter table recording add constraint FKqflt50nekymxmtee3kj9ax9t7 foreign key (id) references long_id_gid_name
alter table recording_alias add constraint FKf3bf0jihug16pfd3n6fksolla foreign key (recording_id) references recording
alter table recording_alias add constraint FKhxhbaha10333uu0e1o6fs02bw foreign key (recording_type_id) references RecordingAliasType
alter table RecordingAliasType add constraint FKgi0bcnu4b95rbnovoat59xglc foreign key (id) references base_type
alter table release add constraint FKrjxedvgqei5yg4s02538jeu55 foreign key (artist_credit_id) references artist_credit
alter table release add constraint FKne7hflamxfjrj3nwcp7rmyqv1 foreign key (language_id) references language
alter table release add constraint FKevsgr1j8wgbldkpylyaaruorq foreign key (packaging_id) references release_packaging
alter table release add constraint FKrdc218r3ti4ecmiwm79k8nrm5 foreign key (release_group_id) references release_group
alter table release add constraint FKqrik1raq7rgbf262jxyuy9m5f foreign key (status_id) references release_status
alter table release add constraint FKphss186b3hrmhvp3yxmht6wym foreign key (id) references long_id_gid_name
alter table release_alias add constraint FK8h783sk9tygkr28w7uaw66hgs foreign key (release_id) references release
alter table release_alias add constraint FK2t0eyhwmmxyhavv0vfc52tsn0 foreign key (type_id) references ReleaseAliasType
alter table release_alias add constraint FKjuo57gcm1hs47dbu7xxh2cqys foreign key (id) references long_id_name
alter table release_alias_begin_date_join add constraint FKcwclxi1f7kynv6aqi09b7k0o4 foreign key (date_id) references composite_dates
alter table release_alias_begin_date_join add constraint FK8fwb728l8xka6plxvd5ioam9v foreign key (release_alias_id) references release_alias
alter table release_alias_end_date_join add constraint FKgk7ci5htcryi5ldvy54kw1p5j foreign key (date_id) references composite_dates
alter table release_alias_end_date_join add constraint FKh23905t8qli78gtd54skm2k32 foreign key (release_alias_id) references release_alias
alter table release_alias_locale_join add constraint FKfu08i10orqtp60yubiuxco5ug foreign key (locale_id) references locale_base
alter table release_alias_locale_join add constraint FKfi3muh2yf67v2e3aavs7o23iy foreign key (release_alias_id) references release_alias
alter table release_alias_sortname_join add constraint FKk0b8uym87fp1se4cwbp8efudx foreign key (sortname_id) references sort_name_base
alter table release_alias_sortname_join add constraint FK47aqy9lhj7y73lnygfx8i5wbv foreign key (release_alias_id) references release_alias
alter table release_barcode_join add constraint FK11ycaqp6hwmm3vvel4pei3hb3 foreign key (barcode_id) references bar_code_base
alter table release_barcode_join add constraint FK1ohu8rea5r4bflvjfo52wuiak foreign key (release_id) references release
alter table release_comment_join add constraint FKcedh69ea15454jb8hfy2pktyw foreign key (barcode_id) references comment_base
alter table release_comment_join add constraint FKaso5up8i49s07ak8mqb1w13bo foreign key (release_id) references release
alter table release_group add constraint FKjhugaa7xl4008xcscrx21v8iw foreign key (artist_credit_id) references artist_credit
alter table release_group add constraint FKngm4tpa9mbjtosws33t7pryhy foreign key (type_id) references release_group_primary_type
alter table release_group add constraint FKch1pyhl900l49xn17d9ff5dym foreign key (id) references long_id_gid_name
alter table release_packaging add constraint FKth87rq87hbka9fr5d380k0bvj foreign key (id) references long_id_gid_name
alter table release_status add constraint FK8269hgk917whc82pabbshqnqk foreign key (id) references long_id_gid_name
alter table ReleaseAliasType add constraint FKpn1yuiiipoklqkwsfv8ysdptd foreign key (id) references base_type
alter table track add constraint FKir00mv1qv09apd7emsparvh9n foreign key (artistCredit_id) references artist_credit
alter table track add constraint FKqxta8bmt9en1errp76u3m2oau foreign key (medium_id) references Medium
alter table track add constraint FKcbmq3cor34r184u84jtfhiw1r foreign key (recordingId) references recording
alter table track add constraint FKj3gkj9wkfhjhi8eh1tf8vsgdb foreign key (id) references long_id_gid_name
alter table track_length_join add constraint FK2jl5eyq1dencmw90asa39hhp foreign key (length_id) references long_lengthy_base
alter table track_length_join add constraint FK3aj1gvdufrwb9snay9cy91t53 foreign key (track_id) references track
alter table track_number_join add constraint FKmwwixblea4b54nrbc2i3ppbut foreign key (number_id) references string_number_base
alter table track_number_join add constraint FKjfj36ofn0l3no50vutks0ha5x foreign key (track_id) references track
alter table track_position_join add constraint FKf2dsts4egfuibtotwmjbmf3tn foreign key (position_id) references long_position_base
alter table track_position_join add constraint FKptd32j597vii6l8900688trkt foreign key (track_id) references track
create sequence artist_credit_name_id_seq start 1 increment 50
create sequence isrc_id_seq start 1 increment 50
create sequence sequence_id_seq start 1 increment 50
create table area (area_id int8 not null, id int8 not null, type_id int8 not null, primary key (id))
create table area_begin_date_join (date_id int8, area_id int8 not null, primary key (area_id))
create table area_comment_join (comment_id int8, area_id int8 not null, primary key (area_id))
create table area_end_date_join (date_id int8, area_id int8 not null, primary key (area_id))
create table AreaType (id int8 not null, primary key (id))
create table artist (artist_id int8 not null, id int8 not null, type_id int8 not null, primary key (id))
create table artist_alias (artist_alias_id int8 not null, id int8 not null, primary key (id))
create table artist_alias_begin_date_join (date_id int8, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_alias_end_date_join (date_id int8, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_alias_join (artist_id int8 not null, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_alias_locale_join (locale_id int8, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_alias_sortname_join (sort_name_id int8 not null, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_alias_type_join (alias_type_id int8 not null, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_area_join (area_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_begin_area_join (area_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_begin_date_join (date_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_comment_join (comment_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_credit (artist_credit_id int8 not null, id int8 not null, primary key (id))
create table artist_credit_count_join (artist_count_id int8 not null, artist_credit_id int8 not null, primary key (artist_credit_id))
create table artist_credit_name (id int8 not null, artist_credit_name_join_prase varchar(2000), ARTIST_NAME varchar(1000), ARTIST_CREDIT_NAME_POSITION int8, artistid int8, artist_credit_id int8, primary key (id))
create table artist_credit_name_rel (artist_credit_id int8 not null, artist_name_id int8 not null)
create table artist_end_area_join (area_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_end_date_join (date_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_gender_join (gender_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_ref_count_join (artist_refcount_id int8 not null, artist_credit_id int8 not null, primary key (artist_credit_id))
create table artist_sortname_join (sort_name_id int8 not null, artist_id int8 not null, primary key (artist_id))
create table ArtistAliasType (id int8 not null, primary key (id))
create table ArtistType (id int8 not null, primary key (id))
create table bar_code_base (table_id VARCHAR NOT NULL not null, id int8 not null, bar_code varchar(255) not null, primary key (id))
create table base_type (childOrder int8, parent int8, type_id int8, id int8 not null, primary key (id))
create table base_type_description_join (description_id int8, base_type_id int8 not null, primary key (base_type_id))
create table comment_base (table_id VARCHAR NOT NULL not null, id int8 not null, comment VARCHAR NOT NULL not null, primary key (id))
create table composite_dates (table_id VARCHAR NOT NULL not null, id int8 not null, day int4, month int4, year int4 not null, primary key (id))
create table description_base (table_id VARCHAR NOT NULL not null, id int8 not null, description VARCHAR NOT NULL not null, primary key (id))
create table description_comment_join (description_id int8, instrument_id int8 not null, primary key (instrument_id))
create table Gender (id int8 not null, primary key (id))
create table instrument (instrument_id int8 not null, id int8 not null, type_id int8 not null, primary key (id))
create table instrument_comment_join (comment_id int8, instrument_id int8 not null, primary key (instrument_id))
create table InstrumentType (id int8 not null, primary key (id))
create table InstrumentType_instrument (InstrumentType_id int8 not null, instruments_id int8 not null, primary key (InstrumentType_id, instruments_id))
create table isrc (id int8 not null, ISRC varchar(255), ISRC_ID int8, RECORDING_ID int8, SOURCE varchar(255), primary key (id))
create table label (id int8 not null, beginDateDay varchar(255), beginDateMonth varchar(255), beginDateYear varchar(255), country varchar(255), endDateDay varchar(255), endDateMonth varchar(255), endDateYear varchar(255), gid varchar(255), ipiCode varchar(255), labelCode varchar(255), labelId int8, name int8, sortName int8, type varchar(255), primary key (id))
create table language (frequency int8, isoCode1 varchar(2), isoCode2b varchar(3), isoCode2t varchar(3), isoCode3 varchar(3), language_id int8, id int8 not null, primary key (id))
create table locale_base (table_id VARCHAR NOT NULL not null, id int8 not null, locale VARCHAR NOT NULL, primary key (id))
create table long_count_base (table_id VARCHAR NOT NULL not null, id int8 not null, count int8 not null, primary key (id))
create table long_id_gid_name (gid varchar(50) not null, id int8 not null, primary key (id))
create table long_id_name (id int8 not null, name VARCHAR NOT NULL not null, primary key (id))
create table long_lengthy_base (table_id VARCHAR NOT NULL not null, id int8 not null, length int8 not null, track_id int8 not null, primary key (id))
create table long_number_base (table_id VARCHAR NOT NULL not null, id int8 not null, number int8 not null, primary key (id))
create table long_position_base (table_id VARCHAR NOT NULL not null, id int8 not null, position int8 not null, primary key (id))
create table Medium (medium_id int8, id int8 not null, medium_format_id int8 not null, release_id int8 not null, primary key (id))
create table medium_count_join (position_id int8 not null, medium_id int8 not null, primary key (medium_id))
create table medium_format (id int8 not null, description varchar(4000), gid varchar(50) not null, hasDiscId varchar(6) not null, name varchar(100) not null, parent int8, year int4, primary key (id))
create table medium_position_join (position_id int8 not null, medium_id int8 not null, primary key (medium_id))
create table recording (recording_id int8 not null, id int8 not null, artist_credit_id int8 not null, recordingLenght_id int8, primary key (id))
create table recording_alias (id int8 not null, begin_date_day int4, begin_date_month int4, begin_date_year int4, end_date_day int4, end_date_month int4, end_date_year int4, locale varchar(1000), name varchar(1000) not null, sortName varchar(1000) not null, recording_id int8 not null, recording_type_id int8, primary key (id))
create table RecordingAliasType (id int8 not null, primary key (id))
create table release (release_id int8 not null, id int8 not null, artist_credit_id int8, language_id int8, packaging_id int8, release_group_id int8 not null, status_id int8, primary key (id))
create table release_alias (relase_alias_id int8 not null, id int8 not null, release_id int8 not null, type_id int8 not null, primary key (id))
create table release_alias_begin_date_join (date_id int8, release_alias_id int8 not null, primary key (release_alias_id))
create table release_alias_end_date_join (date_id int8, release_alias_id int8 not null, primary key (release_alias_id))
create table release_alias_locale_join (locale_id int8, release_alias_id int8 not null, primary key (release_alias_id))
create table release_alias_sortname_join (sortname_id int8 not null, release_alias_id int8 not null, primary key (release_alias_id))
create table release_barcode_join (barcode_id int8, release_id int8 not null, primary key (release_id))
create table release_comment_join (barcode_id int8, release_id int8 not null, primary key (release_id))
create table release_group (release_group_id int8 not null, id int8 not null, artist_credit_id int8 not null, type_id int8, primary key (id))
create table release_group_primary_type (id int8 not null, gid varchar(50) not null, name varchar(255) not null, primary key (id))
create table release_packaging (release_packaging_id int8 not null, id int8 not null, primary key (id))
create table release_status (release_status_id int8 not null, id int8 not null, primary key (id))
create table ReleaseAliasType (id int8 not null, primary key (id))
create table sort_name_base (table_id VARCHAR NOT NULL not null, id int8 not null, sort_name VARCHAR NOT NULL not null, primary key (id))
create table string_number_base (table_id VARCHAR NOT NULL not null, id int8 not null, number VARCHAR NOT NULL, primary key (id))
create table track (track_id int8, id int8 not null, artistCredit_id int8 not null, medium_id int8 not null, recordingId int8 not null, primary key (id))
create table track_length_join (length_id int8 not null, track_id int8 not null, primary key (track_id))
create table track_number_join (number_id int8 not null, track_id int8 not null, primary key (track_id))
create table track_position_join (position_id int8, track_id int8 not null, primary key (track_id))
alter table area add constraint uk_area_id unique (area_id)
alter table artist add constraint uk_artist_id unique (artist_id)
alter table artist_alias add constraint uk_artist_alias_id unique (artist_alias_id)
alter table artist_alias_sortname_join add constraint UK_pj758kjmvfi4w6iylmqqyf87y unique (sort_name_id)
alter table artist_credit add constraint uk_artist_credit_id unique (artist_credit_id)
alter table artist_credit_count_join add constraint UK_b1e6iatyc0a9ebcsnt02cmy6v unique (artist_count_id)
alter table artist_ref_count_join add constraint UK_cjp45m8pm8reo0nj52n626alp unique (artist_refcount_id)
alter table artist_sortname_join add constraint UK_tcbnii28trkvjw0for5lvukji unique (sort_name_id)
create index bar_code_idx on bar_code_base (bar_code)
create index base_type_id_idx on base_type (type_id)
create index comment_table_idx on comment_base (comment)
create index date_year_idx on composite_dates (year)
create index description_table_idx on description_base (description)
create index description_table_id_idx on description_base (table_id)
alter table instrument add constraint uk_instrument_id unique (instrument_id)
alter table InstrumentType_instrument add constraint UK_dgf73kj1tm86puuhtoatiqqkk unique (instruments_id)
alter table language add constraint uk_language_id unique (language_id)
create index locale_table_idx on locale_base (locale)
create index long_count_field_idx on long_count_base (count)
alter table long_id_gid_name add constraint uk_id_gid_name unique (gid)
create index name_idx on long_id_name (name)
create index long_length_table_idx on long_lengthy_base (length)
alter table long_lengthy_base add constraint UK_ns2jmpxdohnw34m6eqpj9q5ny unique (track_id)
create index long_position_position_idx on long_position_base (position)
create index long_position_table_idx on long_position_base (table_id)
alter table Medium add constraint uk_medium_id unique (medium_id)
alter table medium_count_join add constraint UK_r7raky87e1ui5csktub3ecdco unique (position_id)
alter table recording add constraint uk_recording_id unique (recording_id)
alter table release add constraint uk_release_id unique (release_id)
alter table release_group add constraint uk_release_group_id unique (release_group_id)
alter table release_packaging add constraint uk_release_pack_id unique (release_packaging_id)
alter table release_status add constraint uk_release_status_id unique (release_status_id)
create index sort_name_idx on sort_name_base (sort_name)
create index string_number_number_idx on string_number_base (number)
alter table track_length_join add constraint UK_scyko933yrk07ctctpybraddq unique (length_id)
alter table track_number_join add constraint UK_fnkrp16hmvam21nqghfptddt5 unique (number_id)
alter table area add constraint FK36ijraeico3lyy6mtk1sqpi8t foreign key (type_id) references AreaType
alter table area add constraint FK1bgs2s8cvrbmh8sn23l16vj0q foreign key (id) references long_id_gid_name
alter table area_begin_date_join add constraint FKp4uqnvhavqhyyatk5dfrieo6j foreign key (date_id) references composite_dates
alter table area_begin_date_join add constraint FKhxwhfi68h7pm6nw99q60kulwx foreign key (area_id) references area
alter table area_comment_join add constraint FKht1gjphb8hqa33q1rjnh1uyy4 foreign key (comment_id) references comment_base
alter table area_comment_join add constraint FKifb9goxbil6mp8qnlc652fg1o foreign key (area_id) references area
alter table area_end_date_join add constraint FKs9pkgex6hjbjjvognm0jvyve foreign key (date_id) references composite_dates
alter table area_end_date_join add constraint FK2to1rsv7q6jo3oh51adue24sq foreign key (area_id) references area
alter table AreaType add constraint FKr4535o1vubuc9s4gdtcpljl3r foreign key (id) references base_type
alter table artist add constraint FKjft6k91ihgppugrvdu4rb3qe2 foreign key (type_id) references ArtistType
alter table artist add constraint FK5j7iyhq5y0u5bkj6c8i5b3idg foreign key (id) references long_id_gid_name
alter table artist_alias add constraint FKdfpdahx1espsoktxbv5toco1b foreign key (id) references long_id_name
alter table artist_alias_begin_date_join add constraint FKr5yh1fq8o1vaaiaosk1okb3ab foreign key (date_id) references composite_dates
alter table artist_alias_begin_date_join add constraint FKpks05cos5wm1vmqr2pyjt97sf foreign key (artist_alias_id) references artist_alias
alter table artist_alias_end_date_join add constraint FKm5c36tqplh8bwp1qrw0phxv0i foreign key (date_id) references composite_dates
alter table artist_alias_end_date_join add constraint FKmug4f2wcro7htgp48a4iwycu8 foreign key (artist_alias_id) references artist_alias
alter table artist_alias_join add constraint FKgcd77qcx31282qmxeaypm2wl1 foreign key (artist_id) references artist
alter table artist_alias_join add constraint FKjjqk70jqobun8g8ywh9twu8d2 foreign key (artist_alias_id) references artist_alias
alter table artist_alias_locale_join add constraint FKb6xi7sb9l8h80v4qdb52gewf2 foreign key (locale_id) references locale_base
alter table artist_alias_locale_join add constraint FKtle5378eltgllln6n2jimbrrd foreign key (artist_alias_id) references artist_alias
alter table artist_alias_sortname_join add constraint FKtnhfe96x3gvwohh93u88harun foreign key (sort_name_id) references sort_name_base
alter table artist_alias_sortname_join add constraint FKaljjsn3xplko82gxwoyvnhhdg foreign key (artist_alias_id) references artist_alias
alter table artist_alias_type_join add constraint FK61kxfefqhh2u14nev0mq7e2rl foreign key (alias_type_id) references ArtistAliasType
alter table artist_alias_type_join add constraint FKp27ue68w73pcnl22m8cmvopjk foreign key (artist_alias_id) references artist_alias
alter table artist_area_join add constraint FK5v2pynxsg5phk81a9a888f8p6 foreign key (area_id) references area
alter table artist_area_join add constraint FKjwws3jf2jrltwhscmyr4c2c8d foreign key (artist_id) references artist
alter table artist_begin_area_join add constraint FKlib6g7ixl78iper28hwyp4b6j foreign key (area_id) references area
alter table artist_begin_area_join add constraint FKdsqb47nv5mkfer33g0uwjxrg3 foreign key (artist_id) references artist
alter table artist_begin_date_join add constraint FKr4e3bw96hbaq4qopldq91owtr foreign key (date_id) references composite_dates
alter table artist_begin_date_join add constraint FK9ep35foih1qqx5cvjt9lwa1gy foreign key (artist_id) references artist
alter table artist_comment_join add constraint FK5ny27souggg2m4ovikg8ii5jn foreign key (comment_id) references comment_base
alter table artist_comment_join add constraint FKtqvsp3t5j61w068n51iowndiw foreign key (artist_id) references artist
alter table artist_credit add constraint FKsfhccwknqcc8klrsbswt1hdsp foreign key (id) references long_id_name
alter table artist_credit_count_join add constraint FKf79dl4q17hswqhv9y4mb65qat foreign key (artist_count_id) references long_count_base
alter table artist_credit_count_join add constraint FK9pp1ahlexg9hgppy4p3eeit7y foreign key (artist_credit_id) references artist_credit
alter table artist_credit_name add constraint FK7js2hdti9oon8qxw0imp1s0ts foreign key (artistid) references artist
alter table artist_credit_name add constraint FKc8rsbsxnwsngtfqha46qhjlfv foreign key (artist_credit_id) references artist_credit
alter table artist_credit_name_rel add constraint FKn3g0jyj028jrwt6ckpuvk1y5j foreign key (artist_name_id) references artist
alter table artist_credit_name_rel add constraint FK44fgvkqt29bcp2ai5oewfl4p1 foreign key (artist_credit_id) references artist_credit
alter table artist_end_area_join add constraint FKdq9xln44yycsfyifwjhpojy5 foreign key (area_id) references area
alter table artist_end_area_join add constraint FKqpmbpcxmsx9horym1828a6nhm foreign key (artist_id) references artist
alter table artist_end_date_join add constraint FK72tv7jmp2dy6ivoegudsuyann foreign key (date_id) references composite_dates
alter table artist_end_date_join add constraint FK9xnh1e0g4gykda5onwvpl96w0 foreign key (artist_id) references artist
alter table artist_gender_join add constraint FKebm6l5hwjy51kowqos8nevry1 foreign key (gender_id) references Gender
alter table artist_gender_join add constraint FKhmr79ir0dvn6l6g2k4asxe9rh foreign key (artist_id) references artist
alter table artist_ref_count_join add constraint FKlb2e7nxkf00c41ynywljxmuiy foreign key (artist_refcount_id) references long_count_base
alter table artist_ref_count_join add constraint FKsug54x25nuu957axm0c4obs0u foreign key (artist_credit_id) references artist_credit
alter table artist_sortname_join add constraint FK56a9947m1dr2c1j6l52plkspq foreign key (sort_name_id) references sort_name_base
alter table artist_sortname_join add constraint FK6q14bw7t5sywulcx7pcu6bjcs foreign key (artist_id) references artist
alter table ArtistAliasType add constraint FKalono78x6746rw2wcmg6t46k2 foreign key (id) references base_type
alter table ArtistType add constraint FKedp2kuqtci95iw7guvt968j3c foreign key (id) references base_type
alter table base_type add constraint FKodn6b3grgeqmw6b2auasx9n4e foreign key (id) references long_id_gid_name
alter table base_type_description_join add constraint FKckvph0x1homuj7hn7wver3f8k foreign key (description_id) references description_base
alter table base_type_description_join add constraint FK44wmj6925gqqena2mnr2d3c0e foreign key (base_type_id) references base_type
alter table description_comment_join add constraint FKgwus7t07ciceehfgxllpj5qbu foreign key (description_id) references description_base
alter table description_comment_join add constraint FK6m3tg6kbamffc1gx8ptughdsg foreign key (instrument_id) references instrument
alter table Gender add constraint FK5lmp1q9iu740xud1gxangsrg8 foreign key (id) references base_type
alter table instrument add constraint FK94bpiu9rbn67ehf5k349ooksf foreign key (type_id) references InstrumentType
alter table instrument add constraint FKj66ki180cgmuttavrsr0etjlw foreign key (id) references long_id_gid_name
alter table instrument_comment_join add constraint FK2jwbt9rug9qg7co42h1n2wrfx foreign key (comment_id) references comment_base
alter table instrument_comment_join add constraint FK8dawlfgg0km0ofqhs7k8i10rb foreign key (instrument_id) references instrument
alter table InstrumentType add constraint FKifirbapdceb91xqv0vibhd3vq foreign key (id) references base_type
alter table InstrumentType_instrument add constraint FKktv0l7uftgcf6ncyjxx7w6rjq foreign key (instruments_id) references instrument
alter table InstrumentType_instrument add constraint FK9kufqmeecpl0ho81sffyn9y8d foreign key (InstrumentType_id) references InstrumentType
alter table language add constraint FKtb4qe7sw6ccf7pwoj11hfslx2 foreign key (id) references long_id_name
alter table long_id_gid_name add constraint FKse71nfag7h861smx8ju4becl9 foreign key (id) references long_id_name
alter table long_lengthy_base add constraint FKeoqyoydaygg3uc0uflpedqpjb foreign key (track_id) references track
alter table Medium add constraint FK88xlaa32r1xxpm9vykwh3675e foreign key (medium_format_id) references medium_format
alter table Medium add constraint FKkhs58g5n5bnyqr14hklutr1jc foreign key (release_id) references release
alter table Medium add constraint FKd29oftthn2kc1h93yvak4o8oh foreign key (id) references long_id_name
alter table medium_count_join add constraint FKnb6vfdqw575q0c53w4c43bae1 foreign key (position_id) references long_count_base
alter table medium_count_join add constraint FK5gnqnfnwrqj7uuio47742wsqt foreign key (medium_id) references Medium
alter table medium_position_join add constraint FK7npic7e0xpkw01dji0v59jimt foreign key (position_id) references long_position_base
alter table medium_position_join add constraint FKqpxdjbhc5y2t6sb9g0d6sswg2 foreign key (medium_id) references Medium
alter table recording add constraint FKwhnq53y2fteeexfpar6558ut foreign key (artist_credit_id) references artist_credit
alter table recording add constraint FKtdh05j1q5nmsl9q7mo77gtssp foreign key (recordingLenght_id) references long_lengthy_base
alter table recording add constraint FKqflt50nekymxmtee3kj9ax9t7 foreign key (id) references long_id_gid_name
alter table recording_alias add constraint FKf3bf0jihug16pfd3n6fksolla foreign key (recording_id) references recording
alter table recording_alias add constraint FKhxhbaha10333uu0e1o6fs02bw foreign key (recording_type_id) references RecordingAliasType
alter table RecordingAliasType add constraint FKgi0bcnu4b95rbnovoat59xglc foreign key (id) references base_type
alter table release add constraint FKrjxedvgqei5yg4s02538jeu55 foreign key (artist_credit_id) references artist_credit
alter table release add constraint FKne7hflamxfjrj3nwcp7rmyqv1 foreign key (language_id) references language
alter table release add constraint FKevsgr1j8wgbldkpylyaaruorq foreign key (packaging_id) references release_packaging
alter table release add constraint FKrdc218r3ti4ecmiwm79k8nrm5 foreign key (release_group_id) references release_group
alter table release add constraint FKqrik1raq7rgbf262jxyuy9m5f foreign key (status_id) references release_status
alter table release add constraint FKphss186b3hrmhvp3yxmht6wym foreign key (id) references long_id_gid_name
alter table release_alias add constraint FK8h783sk9tygkr28w7uaw66hgs foreign key (release_id) references release
alter table release_alias add constraint FK2t0eyhwmmxyhavv0vfc52tsn0 foreign key (type_id) references ReleaseAliasType
alter table release_alias add constraint FKjuo57gcm1hs47dbu7xxh2cqys foreign key (id) references long_id_name
alter table release_alias_begin_date_join add constraint FKcwclxi1f7kynv6aqi09b7k0o4 foreign key (date_id) references composite_dates
alter table release_alias_begin_date_join add constraint FK8fwb728l8xka6plxvd5ioam9v foreign key (release_alias_id) references release_alias
alter table release_alias_end_date_join add constraint FKgk7ci5htcryi5ldvy54kw1p5j foreign key (date_id) references composite_dates
alter table release_alias_end_date_join add constraint FKh23905t8qli78gtd54skm2k32 foreign key (release_alias_id) references release_alias
alter table release_alias_locale_join add constraint FKfu08i10orqtp60yubiuxco5ug foreign key (locale_id) references locale_base
alter table release_alias_locale_join add constraint FKfi3muh2yf67v2e3aavs7o23iy foreign key (release_alias_id) references release_alias
alter table release_alias_sortname_join add constraint FKk0b8uym87fp1se4cwbp8efudx foreign key (sortname_id) references sort_name_base
alter table release_alias_sortname_join add constraint FK47aqy9lhj7y73lnygfx8i5wbv foreign key (release_alias_id) references release_alias
alter table release_barcode_join add constraint FK11ycaqp6hwmm3vvel4pei3hb3 foreign key (barcode_id) references bar_code_base
alter table release_barcode_join add constraint FK1ohu8rea5r4bflvjfo52wuiak foreign key (release_id) references release
alter table release_comment_join add constraint FKcedh69ea15454jb8hfy2pktyw foreign key (barcode_id) references comment_base
alter table release_comment_join add constraint FKaso5up8i49s07ak8mqb1w13bo foreign key (release_id) references release
alter table release_group add constraint FKjhugaa7xl4008xcscrx21v8iw foreign key (artist_credit_id) references artist_credit
alter table release_group add constraint FKngm4tpa9mbjtosws33t7pryhy foreign key (type_id) references release_group_primary_type
alter table release_group add constraint FKch1pyhl900l49xn17d9ff5dym foreign key (id) references long_id_gid_name
alter table release_packaging add constraint FKth87rq87hbka9fr5d380k0bvj foreign key (id) references long_id_gid_name
alter table release_status add constraint FK8269hgk917whc82pabbshqnqk foreign key (id) references long_id_gid_name
alter table ReleaseAliasType add constraint FKpn1yuiiipoklqkwsfv8ysdptd foreign key (id) references base_type
alter table track add constraint FKir00mv1qv09apd7emsparvh9n foreign key (artistCredit_id) references artist_credit
alter table track add constraint FKqxta8bmt9en1errp76u3m2oau foreign key (medium_id) references Medium
alter table track add constraint FKcbmq3cor34r184u84jtfhiw1r foreign key (recordingId) references recording
alter table track add constraint FKj3gkj9wkfhjhi8eh1tf8vsgdb foreign key (id) references long_id_gid_name
alter table track_length_join add constraint FK2jl5eyq1dencmw90asa39hhp foreign key (length_id) references long_lengthy_base
alter table track_length_join add constraint FK3aj1gvdufrwb9snay9cy91t53 foreign key (track_id) references track
alter table track_number_join add constraint FKmwwixblea4b54nrbc2i3ppbut foreign key (number_id) references string_number_base
alter table track_number_join add constraint FKjfj36ofn0l3no50vutks0ha5x foreign key (track_id) references track
alter table track_position_join add constraint FKf2dsts4egfuibtotwmjbmf3tn foreign key (position_id) references long_position_base
alter table track_position_join add constraint FKptd32j597vii6l8900688trkt foreign key (track_id) references track
create sequence artist_credit_name_id_seq start 1 increment 50
create sequence isrc_id_seq start 1 increment 50
create sequence sequence_id_seq start 1 increment 50
create table area (area_id int8 not null, id int8 not null, type_id int8 not null, primary key (id))
create table area_begin_date_join (date_id int8, area_id int8 not null, primary key (area_id))
create table area_comment_join (comment_id int8, area_id int8 not null, primary key (area_id))
create table area_end_date_join (date_id int8, area_id int8 not null, primary key (area_id))
create table AreaType (id int8 not null, primary key (id))
create table artist (artist_id int8 not null, id int8 not null, type_id int8 not null, primary key (id))
create table artist_alias (artist_alias_id int8 not null, id int8 not null, primary key (id))
create table artist_alias_begin_date_join (date_id int8, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_alias_end_date_join (date_id int8, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_alias_join (artist_id int8 not null, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_alias_locale_join (locale_id int8, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_alias_sortname_join (sort_name_id int8 not null, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_alias_type_join (alias_type_id int8 not null, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_area_join (area_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_begin_area_join (area_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_begin_date_join (date_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_comment_join (comment_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_credit (artist_credit_id int8 not null, id int8 not null, primary key (id))
create table artist_credit_count_join (artist_count_id int8 not null, artist_credit_id int8 not null, primary key (artist_credit_id))
create table artist_credit_name (id int8 not null, artist_credit_name_join_prase varchar(2000), ARTIST_NAME varchar(1000), ARTIST_CREDIT_NAME_POSITION int8, artistid int8, artist_credit_id int8, primary key (id))
create table artist_credit_name_rel (artist_credit_id int8 not null, artist_name_id int8 not null)
create table artist_end_area_join (area_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_end_date_join (date_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_gender_join (gender_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_ref_count_join (artist_refcount_id int8 not null, artist_credit_id int8 not null, primary key (artist_credit_id))
create table artist_sortname_join (sort_name_id int8 not null, artist_id int8 not null, primary key (artist_id))
create table ArtistAliasType (id int8 not null, primary key (id))
create table ArtistType (id int8 not null, primary key (id))
create table bar_code_base (table_id VARCHAR NOT NULL not null, id int8 not null, bar_code varchar(255) not null, primary key (id))
create table base_type (childOrder int8, parent int8, type_id int8, id int8 not null, primary key (id))
create table base_type_description_join (description_id int8, base_type_id int8 not null, primary key (base_type_id))
create table comment_base (table_id VARCHAR NOT NULL not null, id int8 not null, comment VARCHAR NOT NULL not null, primary key (id))
create table composite_dates (table_id VARCHAR NOT NULL not null, id int8 not null, day int4, month int4, year int4 not null, primary key (id))
create table description_base (table_id VARCHAR NOT NULL not null, id int8 not null, description VARCHAR NOT NULL not null, primary key (id))
create table description_comment_join (description_id int8, instrument_id int8 not null, primary key (instrument_id))
create table Gender (id int8 not null, primary key (id))
create table instrument (instrument_id int8 not null, id int8 not null, type_id int8 not null, primary key (id))
create table instrument_comment_join (comment_id int8, instrument_id int8 not null, primary key (instrument_id))
create table InstrumentType (id int8 not null, primary key (id))
create table InstrumentType_instrument (InstrumentType_id int8 not null, instruments_id int8 not null, primary key (InstrumentType_id, instruments_id))
create table isrc (id int8 not null, ISRC varchar(255), ISRC_ID int8, RECORDING_ID int8, SOURCE varchar(255), primary key (id))
create table label (id int8 not null, beginDateDay varchar(255), beginDateMonth varchar(255), beginDateYear varchar(255), country varchar(255), endDateDay varchar(255), endDateMonth varchar(255), endDateYear varchar(255), gid varchar(255), ipiCode varchar(255), labelCode varchar(255), labelId int8, name int8, sortName int8, type varchar(255), primary key (id))
create table language (frequency int8, isoCode1 varchar(2), isoCode2b varchar(3), isoCode2t varchar(3), isoCode3 varchar(3), language_id int8, id int8 not null, primary key (id))
create table locale_base (table_id VARCHAR NOT NULL not null, id int8 not null, locale VARCHAR NOT NULL, primary key (id))
create table long_count_base (table_id VARCHAR NOT NULL not null, id int8 not null, count int8 not null, primary key (id))
create table long_id_gid_name (gid varchar(50) not null, id int8 not null, primary key (id))
create table long_id_name (id int8 not null, name VARCHAR NOT NULL not null, primary key (id))
create table long_lengthy_base (table_id VARCHAR NOT NULL not null, id int8 not null, length int8 not null, track_id int8 not null, primary key (id))
create table long_number_base (table_id VARCHAR NOT NULL not null, id int8 not null, number int8 not null, primary key (id))
create table long_position_base (table_id VARCHAR NOT NULL not null, id int8 not null, position int8 not null, primary key (id))
create table Medium (medium_id int8, id int8 not null, medium_format_id int8 not null, release_id int8 not null, primary key (id))
create table medium_count_join (position_id int8 not null, medium_id int8 not null, primary key (medium_id))
create table medium_format (id int8 not null, description varchar(4000), gid varchar(50) not null, hasDiscId varchar(6) not null, name varchar(100) not null, parent int8, year int4, primary key (id))
create table medium_position_join (position_id int8 not null, medium_id int8 not null, primary key (medium_id))
create table recording (recording_id int8 not null, id int8 not null, artist_credit_id int8 not null, recordingLenght_id int8, primary key (id))
create table recording_alias (id int8 not null, begin_date_day int4, begin_date_month int4, begin_date_year int4, end_date_day int4, end_date_month int4, end_date_year int4, locale varchar(1000), name varchar(1000) not null, sortName varchar(1000) not null, recording_id int8 not null, recording_type_id int8, primary key (id))
create table RecordingAliasType (id int8 not null, primary key (id))
create table release (release_id int8 not null, id int8 not null, artist_credit_id int8, language_id int8, packaging_id int8, release_group_id int8 not null, status_id int8, primary key (id))
create table release_alias (relase_alias_id int8 not null, id int8 not null, release_id int8 not null, type_id int8 not null, primary key (id))
create table release_alias_begin_date_join (date_id int8, release_alias_id int8 not null, primary key (release_alias_id))
create table release_alias_end_date_join (date_id int8, release_alias_id int8 not null, primary key (release_alias_id))
create table release_alias_locale_join (locale_id int8, release_alias_id int8 not null, primary key (release_alias_id))
create table release_alias_sortname_join (sortname_id int8 not null, release_alias_id int8 not null, primary key (release_alias_id))
create table release_barcode_join (barcode_id int8, release_id int8 not null, primary key (release_id))
create table release_comment_join (barcode_id int8, release_id int8 not null, primary key (release_id))
create table release_group (release_group_id int8 not null, id int8 not null, artist_credit_id int8 not null, type_id int8, primary key (id))
create table release_group_primary_type (id int8 not null, gid varchar(50) not null, name varchar(255) not null, primary key (id))
create table release_packaging (release_packaging_id int8 not null, id int8 not null, primary key (id))
create table release_status (release_status_id int8 not null, id int8 not null, primary key (id))
create table ReleaseAliasType (id int8 not null, primary key (id))
create table sort_name_base (table_id VARCHAR NOT NULL not null, id int8 not null, sort_name VARCHAR NOT NULL not null, primary key (id))
create table string_number_base (table_id VARCHAR NOT NULL not null, id int8 not null, number VARCHAR NOT NULL, primary key (id))
create table track (track_id int8, id int8 not null, artistCredit_id int8 not null, medium_id int8 not null, recordingId int8 not null, primary key (id))
create table track_length_join (length_id int8 not null, track_id int8 not null, primary key (track_id))
create table track_number_join (number_id int8 not null, track_id int8 not null, primary key (track_id))
create table track_position_join (position_id int8, track_id int8 not null, primary key (track_id))
alter table area add constraint uk_area_id unique (area_id)
alter table artist add constraint uk_artist_id unique (artist_id)
alter table artist_alias add constraint uk_artist_alias_id unique (artist_alias_id)
alter table artist_alias_sortname_join add constraint UK_pj758kjmvfi4w6iylmqqyf87y unique (sort_name_id)
alter table artist_credit add constraint uk_artist_credit_id unique (artist_credit_id)
alter table artist_credit_count_join add constraint UK_b1e6iatyc0a9ebcsnt02cmy6v unique (artist_count_id)
alter table artist_ref_count_join add constraint UK_cjp45m8pm8reo0nj52n626alp unique (artist_refcount_id)
alter table artist_sortname_join add constraint UK_tcbnii28trkvjw0for5lvukji unique (sort_name_id)
create index bar_code_idx on bar_code_base (bar_code)
create index base_type_id_idx on base_type (type_id)
create index comment_table_idx on comment_base (comment)
create index date_year_idx on composite_dates (year)
create index description_table_idx on description_base (description)
create index description_table_id_idx on description_base (table_id)
alter table instrument add constraint uk_instrument_id unique (instrument_id)
alter table InstrumentType_instrument add constraint UK_dgf73kj1tm86puuhtoatiqqkk unique (instruments_id)
alter table language add constraint uk_language_id unique (language_id)
create index locale_table_idx on locale_base (locale)
create index long_count_field_idx on long_count_base (count)
alter table long_id_gid_name add constraint uk_id_gid_name unique (gid)
create index name_idx on long_id_name (name)
create index long_length_table_idx on long_lengthy_base (length)
alter table long_lengthy_base add constraint UK_ns2jmpxdohnw34m6eqpj9q5ny unique (track_id)
create index long_position_position_idx on long_position_base (position)
create index long_position_table_idx on long_position_base (table_id)
alter table Medium add constraint uk_medium_id unique (medium_id)
alter table medium_count_join add constraint UK_r7raky87e1ui5csktub3ecdco unique (position_id)
alter table recording add constraint uk_recording_id unique (recording_id)
alter table release add constraint uk_release_id unique (release_id)
alter table release_group add constraint uk_release_group_id unique (release_group_id)
alter table release_packaging add constraint uk_release_pack_id unique (release_packaging_id)
alter table release_status add constraint uk_release_status_id unique (release_status_id)
create index sort_name_idx on sort_name_base (sort_name)
create index string_number_number_idx on string_number_base (number)
alter table track_length_join add constraint UK_scyko933yrk07ctctpybraddq unique (length_id)
alter table track_number_join add constraint UK_fnkrp16hmvam21nqghfptddt5 unique (number_id)
alter table area add constraint FK36ijraeico3lyy6mtk1sqpi8t foreign key (type_id) references AreaType
alter table area add constraint FK1bgs2s8cvrbmh8sn23l16vj0q foreign key (id) references long_id_gid_name
alter table area_begin_date_join add constraint FKp4uqnvhavqhyyatk5dfrieo6j foreign key (date_id) references composite_dates
alter table area_begin_date_join add constraint FKhxwhfi68h7pm6nw99q60kulwx foreign key (area_id) references area
alter table area_comment_join add constraint FKht1gjphb8hqa33q1rjnh1uyy4 foreign key (comment_id) references comment_base
alter table area_comment_join add constraint FKifb9goxbil6mp8qnlc652fg1o foreign key (area_id) references area
alter table area_end_date_join add constraint FKs9pkgex6hjbjjvognm0jvyve foreign key (date_id) references composite_dates
alter table area_end_date_join add constraint FK2to1rsv7q6jo3oh51adue24sq foreign key (area_id) references area
alter table AreaType add constraint FKr4535o1vubuc9s4gdtcpljl3r foreign key (id) references base_type
alter table artist add constraint FKjft6k91ihgppugrvdu4rb3qe2 foreign key (type_id) references ArtistType
alter table artist add constraint FK5j7iyhq5y0u5bkj6c8i5b3idg foreign key (id) references long_id_gid_name
alter table artist_alias add constraint FKdfpdahx1espsoktxbv5toco1b foreign key (id) references long_id_name
alter table artist_alias_begin_date_join add constraint FKr5yh1fq8o1vaaiaosk1okb3ab foreign key (date_id) references composite_dates
alter table artist_alias_begin_date_join add constraint FKpks05cos5wm1vmqr2pyjt97sf foreign key (artist_alias_id) references artist_alias
alter table artist_alias_end_date_join add constraint FKm5c36tqplh8bwp1qrw0phxv0i foreign key (date_id) references composite_dates
alter table artist_alias_end_date_join add constraint FKmug4f2wcro7htgp48a4iwycu8 foreign key (artist_alias_id) references artist_alias
alter table artist_alias_join add constraint FKgcd77qcx31282qmxeaypm2wl1 foreign key (artist_id) references artist
alter table artist_alias_join add constraint FKjjqk70jqobun8g8ywh9twu8d2 foreign key (artist_alias_id) references artist_alias
alter table artist_alias_locale_join add constraint FKb6xi7sb9l8h80v4qdb52gewf2 foreign key (locale_id) references locale_base
alter table artist_alias_locale_join add constraint FKtle5378eltgllln6n2jimbrrd foreign key (artist_alias_id) references artist_alias
alter table artist_alias_sortname_join add constraint FKtnhfe96x3gvwohh93u88harun foreign key (sort_name_id) references sort_name_base
alter table artist_alias_sortname_join add constraint FKaljjsn3xplko82gxwoyvnhhdg foreign key (artist_alias_id) references artist_alias
alter table artist_alias_type_join add constraint FK61kxfefqhh2u14nev0mq7e2rl foreign key (alias_type_id) references ArtistAliasType
alter table artist_alias_type_join add constraint FKp27ue68w73pcnl22m8cmvopjk foreign key (artist_alias_id) references artist_alias
alter table artist_area_join add constraint FK5v2pynxsg5phk81a9a888f8p6 foreign key (area_id) references area
alter table artist_area_join add constraint FKjwws3jf2jrltwhscmyr4c2c8d foreign key (artist_id) references artist
alter table artist_begin_area_join add constraint FKlib6g7ixl78iper28hwyp4b6j foreign key (area_id) references area
alter table artist_begin_area_join add constraint FKdsqb47nv5mkfer33g0uwjxrg3 foreign key (artist_id) references artist
alter table artist_begin_date_join add constraint FKr4e3bw96hbaq4qopldq91owtr foreign key (date_id) references composite_dates
alter table artist_begin_date_join add constraint FK9ep35foih1qqx5cvjt9lwa1gy foreign key (artist_id) references artist
alter table artist_comment_join add constraint FK5ny27souggg2m4ovikg8ii5jn foreign key (comment_id) references comment_base
alter table artist_comment_join add constraint FKtqvsp3t5j61w068n51iowndiw foreign key (artist_id) references artist
alter table artist_credit add constraint FKsfhccwknqcc8klrsbswt1hdsp foreign key (id) references long_id_name
alter table artist_credit_count_join add constraint FKf79dl4q17hswqhv9y4mb65qat foreign key (artist_count_id) references long_count_base
alter table artist_credit_count_join add constraint FK9pp1ahlexg9hgppy4p3eeit7y foreign key (artist_credit_id) references artist_credit
alter table artist_credit_name add constraint FK7js2hdti9oon8qxw0imp1s0ts foreign key (artistid) references artist
alter table artist_credit_name add constraint FKc8rsbsxnwsngtfqha46qhjlfv foreign key (artist_credit_id) references artist_credit
alter table artist_credit_name_rel add constraint FKn3g0jyj028jrwt6ckpuvk1y5j foreign key (artist_name_id) references artist
alter table artist_credit_name_rel add constraint FK44fgvkqt29bcp2ai5oewfl4p1 foreign key (artist_credit_id) references artist_credit
alter table artist_end_area_join add constraint FKdq9xln44yycsfyifwjhpojy5 foreign key (area_id) references area
alter table artist_end_area_join add constraint FKqpmbpcxmsx9horym1828a6nhm foreign key (artist_id) references artist
alter table artist_end_date_join add constraint FK72tv7jmp2dy6ivoegudsuyann foreign key (date_id) references composite_dates
alter table artist_end_date_join add constraint FK9xnh1e0g4gykda5onwvpl96w0 foreign key (artist_id) references artist
alter table artist_gender_join add constraint FKebm6l5hwjy51kowqos8nevry1 foreign key (gender_id) references Gender
alter table artist_gender_join add constraint FKhmr79ir0dvn6l6g2k4asxe9rh foreign key (artist_id) references artist
alter table artist_ref_count_join add constraint FKlb2e7nxkf00c41ynywljxmuiy foreign key (artist_refcount_id) references long_count_base
alter table artist_ref_count_join add constraint FKsug54x25nuu957axm0c4obs0u foreign key (artist_credit_id) references artist_credit
alter table artist_sortname_join add constraint FK56a9947m1dr2c1j6l52plkspq foreign key (sort_name_id) references sort_name_base
alter table artist_sortname_join add constraint FK6q14bw7t5sywulcx7pcu6bjcs foreign key (artist_id) references artist
alter table ArtistAliasType add constraint FKalono78x6746rw2wcmg6t46k2 foreign key (id) references base_type
alter table ArtistType add constraint FKedp2kuqtci95iw7guvt968j3c foreign key (id) references base_type
alter table base_type add constraint FKodn6b3grgeqmw6b2auasx9n4e foreign key (id) references long_id_gid_name
alter table base_type_description_join add constraint FKckvph0x1homuj7hn7wver3f8k foreign key (description_id) references description_base
alter table base_type_description_join add constraint FK44wmj6925gqqena2mnr2d3c0e foreign key (base_type_id) references base_type
alter table description_comment_join add constraint FKgwus7t07ciceehfgxllpj5qbu foreign key (description_id) references description_base
alter table description_comment_join add constraint FK6m3tg6kbamffc1gx8ptughdsg foreign key (instrument_id) references instrument
alter table Gender add constraint FK5lmp1q9iu740xud1gxangsrg8 foreign key (id) references base_type
alter table instrument add constraint FK94bpiu9rbn67ehf5k349ooksf foreign key (type_id) references InstrumentType
alter table instrument add constraint FKj66ki180cgmuttavrsr0etjlw foreign key (id) references long_id_gid_name
alter table instrument_comment_join add constraint FK2jwbt9rug9qg7co42h1n2wrfx foreign key (comment_id) references comment_base
alter table instrument_comment_join add constraint FK8dawlfgg0km0ofqhs7k8i10rb foreign key (instrument_id) references instrument
alter table InstrumentType add constraint FKifirbapdceb91xqv0vibhd3vq foreign key (id) references base_type
alter table InstrumentType_instrument add constraint FKktv0l7uftgcf6ncyjxx7w6rjq foreign key (instruments_id) references instrument
alter table InstrumentType_instrument add constraint FK9kufqmeecpl0ho81sffyn9y8d foreign key (InstrumentType_id) references InstrumentType
alter table language add constraint FKtb4qe7sw6ccf7pwoj11hfslx2 foreign key (id) references long_id_name
alter table long_id_gid_name add constraint FKse71nfag7h861smx8ju4becl9 foreign key (id) references long_id_name
alter table long_lengthy_base add constraint FKeoqyoydaygg3uc0uflpedqpjb foreign key (track_id) references track
alter table Medium add constraint FK88xlaa32r1xxpm9vykwh3675e foreign key (medium_format_id) references medium_format
alter table Medium add constraint FKkhs58g5n5bnyqr14hklutr1jc foreign key (release_id) references release
alter table Medium add constraint FKd29oftthn2kc1h93yvak4o8oh foreign key (id) references long_id_name
alter table medium_count_join add constraint FKnb6vfdqw575q0c53w4c43bae1 foreign key (position_id) references long_count_base
alter table medium_count_join add constraint FK5gnqnfnwrqj7uuio47742wsqt foreign key (medium_id) references Medium
alter table medium_position_join add constraint FK7npic7e0xpkw01dji0v59jimt foreign key (position_id) references long_position_base
alter table medium_position_join add constraint FKqpxdjbhc5y2t6sb9g0d6sswg2 foreign key (medium_id) references Medium
alter table recording add constraint FKwhnq53y2fteeexfpar6558ut foreign key (artist_credit_id) references artist_credit
alter table recording add constraint FKtdh05j1q5nmsl9q7mo77gtssp foreign key (recordingLenght_id) references long_lengthy_base
alter table recording add constraint FKqflt50nekymxmtee3kj9ax9t7 foreign key (id) references long_id_gid_name
alter table recording_alias add constraint FKf3bf0jihug16pfd3n6fksolla foreign key (recording_id) references recording
alter table recording_alias add constraint FKhxhbaha10333uu0e1o6fs02bw foreign key (recording_type_id) references RecordingAliasType
alter table RecordingAliasType add constraint FKgi0bcnu4b95rbnovoat59xglc foreign key (id) references base_type
alter table release add constraint FKrjxedvgqei5yg4s02538jeu55 foreign key (artist_credit_id) references artist_credit
alter table release add constraint FKne7hflamxfjrj3nwcp7rmyqv1 foreign key (language_id) references language
alter table release add constraint FKevsgr1j8wgbldkpylyaaruorq foreign key (packaging_id) references release_packaging
alter table release add constraint FKrdc218r3ti4ecmiwm79k8nrm5 foreign key (release_group_id) references release_group
alter table release add constraint FKqrik1raq7rgbf262jxyuy9m5f foreign key (status_id) references release_status
alter table release add constraint FKphss186b3hrmhvp3yxmht6wym foreign key (id) references long_id_gid_name
alter table release_alias add constraint FK8h783sk9tygkr28w7uaw66hgs foreign key (release_id) references release
alter table release_alias add constraint FK2t0eyhwmmxyhavv0vfc52tsn0 foreign key (type_id) references ReleaseAliasType
alter table release_alias add constraint FKjuo57gcm1hs47dbu7xxh2cqys foreign key (id) references long_id_name
alter table release_alias_begin_date_join add constraint FKcwclxi1f7kynv6aqi09b7k0o4 foreign key (date_id) references composite_dates
alter table release_alias_begin_date_join add constraint FK8fwb728l8xka6plxvd5ioam9v foreign key (release_alias_id) references release_alias
alter table release_alias_end_date_join add constraint FKgk7ci5htcryi5ldvy54kw1p5j foreign key (date_id) references composite_dates
alter table release_alias_end_date_join add constraint FKh23905t8qli78gtd54skm2k32 foreign key (release_alias_id) references release_alias
alter table release_alias_locale_join add constraint FKfu08i10orqtp60yubiuxco5ug foreign key (locale_id) references locale_base
alter table release_alias_locale_join add constraint FKfi3muh2yf67v2e3aavs7o23iy foreign key (release_alias_id) references release_alias
alter table release_alias_sortname_join add constraint FKk0b8uym87fp1se4cwbp8efudx foreign key (sortname_id) references sort_name_base
alter table release_alias_sortname_join add constraint FK47aqy9lhj7y73lnygfx8i5wbv foreign key (release_alias_id) references release_alias
alter table release_barcode_join add constraint FK11ycaqp6hwmm3vvel4pei3hb3 foreign key (barcode_id) references bar_code_base
alter table release_barcode_join add constraint FK1ohu8rea5r4bflvjfo52wuiak foreign key (release_id) references release
alter table release_comment_join add constraint FKcedh69ea15454jb8hfy2pktyw foreign key (barcode_id) references comment_base
alter table release_comment_join add constraint FKaso5up8i49s07ak8mqb1w13bo foreign key (release_id) references release
alter table release_group add constraint FKjhugaa7xl4008xcscrx21v8iw foreign key (artist_credit_id) references artist_credit
alter table release_group add constraint FKngm4tpa9mbjtosws33t7pryhy foreign key (type_id) references release_group_primary_type
alter table release_group add constraint FKch1pyhl900l49xn17d9ff5dym foreign key (id) references long_id_gid_name
alter table release_packaging add constraint FKth87rq87hbka9fr5d380k0bvj foreign key (id) references long_id_gid_name
alter table release_status add constraint FK8269hgk917whc82pabbshqnqk foreign key (id) references long_id_gid_name
alter table ReleaseAliasType add constraint FKpn1yuiiipoklqkwsfv8ysdptd foreign key (id) references base_type
alter table track add constraint FKir00mv1qv09apd7emsparvh9n foreign key (artistCredit_id) references artist_credit
alter table track add constraint FKqxta8bmt9en1errp76u3m2oau foreign key (medium_id) references Medium
alter table track add constraint FKcbmq3cor34r184u84jtfhiw1r foreign key (recordingId) references recording
alter table track add constraint FKj3gkj9wkfhjhi8eh1tf8vsgdb foreign key (id) references long_id_gid_name
alter table track_length_join add constraint FK2jl5eyq1dencmw90asa39hhp foreign key (length_id) references long_lengthy_base
alter table track_length_join add constraint FK3aj1gvdufrwb9snay9cy91t53 foreign key (track_id) references track
alter table track_number_join add constraint FKmwwixblea4b54nrbc2i3ppbut foreign key (number_id) references string_number_base
alter table track_number_join add constraint FKjfj36ofn0l3no50vutks0ha5x foreign key (track_id) references track
alter table track_position_join add constraint FKf2dsts4egfuibtotwmjbmf3tn foreign key (position_id) references long_position_base
alter table track_position_join add constraint FKptd32j597vii6l8900688trkt foreign key (track_id) references track
create sequence artist_credit_name_id_seq start 1 increment 50
create sequence isrc_id_seq start 1 increment 50
create sequence sequence_id_seq start 1 increment 50
create table area (area_id int8 not null, id int8 not null, type_id int8 not null, primary key (id))
create table area_begin_date_join (date_id int8, area_id int8 not null, primary key (area_id))
create table area_comment_join (comment_id int8, area_id int8 not null, primary key (area_id))
create table area_end_date_join (date_id int8, area_id int8 not null, primary key (area_id))
create table AreaType (id int8 not null, primary key (id))
create table artist (artist_id int8 not null, id int8 not null, type_id int8 not null, primary key (id))
create table artist_alias (artist_alias_id int8 not null, id int8 not null, primary key (id))
create table artist_alias_begin_date_join (date_id int8, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_alias_end_date_join (date_id int8, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_alias_join (artist_id int8 not null, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_alias_locale_join (locale_id int8, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_alias_sortname_join (sort_name_id int8 not null, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_alias_type_join (alias_type_id int8 not null, artist_alias_id int8 not null, primary key (artist_alias_id))
create table artist_area_join (area_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_begin_area_join (area_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_begin_date_join (date_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_comment_join (comment_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_credit (artist_credit_id int8 not null, id int8 not null, primary key (id))
create table artist_credit_count_join (artist_count_id int8 not null, artist_credit_id int8 not null, primary key (artist_credit_id))
create table artist_credit_name (id int8 not null, artist_credit_name_join_prase varchar(2000), ARTIST_NAME varchar(1000), ARTIST_CREDIT_NAME_POSITION int8, artistid int8, artist_credit_id int8, primary key (id))
create table artist_credit_name_rel (artist_credit_id int8 not null, artist_name_id int8 not null)
create table artist_end_area_join (area_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_end_date_join (date_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_gender_join (gender_id int8, artist_id int8 not null, primary key (artist_id))
create table artist_ref_count_join (artist_refcount_id int8 not null, artist_credit_id int8 not null, primary key (artist_credit_id))
create table artist_sortname_join (sort_name_id int8 not null, artist_id int8 not null, primary key (artist_id))
create table ArtistAliasType (id int8 not null, primary key (id))
create table ArtistType (id int8 not null, primary key (id))
create table bar_code_base (table_id VARCHAR NOT NULL not null, id int8 not null, bar_code varchar(255) not null, primary key (id))
create table base_type (childOrder int8, parent int8, type_id int8, id int8 not null, primary key (id))
create table base_type_description_join (description_id int8, base_type_id int8 not null, primary key (base_type_id))
create table comment_base (table_id VARCHAR NOT NULL not null, id int8 not null, comment VARCHAR NOT NULL not null, primary key (id))
create table composite_dates (table_id VARCHAR NOT NULL not null, id int8 not null, day int4, month int4, year int4 not null, primary key (id))
create table description_base (table_id VARCHAR NOT NULL not null, id int8 not null, description VARCHAR NOT NULL not null, primary key (id))
create table description_comment_join (description_id int8, instrument_id int8 not null, primary key (instrument_id))
create table Gender (id int8 not null, primary key (id))
create table instrument (instrument_id int8 not null, id int8 not null, type_id int8 not null, primary key (id))
create table instrument_comment_join (comment_id int8, instrument_id int8 not null, primary key (instrument_id))
create table InstrumentType (id int8 not null, primary key (id))
create table InstrumentType_instrument (InstrumentType_id int8 not null, instruments_id int8 not null, primary key (InstrumentType_id, instruments_id))
create table isrc (id int8 not null, ISRC varchar(255), ISRC_ID int8, RECORDING_ID int8, SOURCE varchar(255), primary key (id))
create table label (id int8 not null, beginDateDay varchar(255), beginDateMonth varchar(255), beginDateYear varchar(255), country varchar(255), endDateDay varchar(255), endDateMonth varchar(255), endDateYear varchar(255), gid varchar(255), ipiCode varchar(255), labelCode varchar(255), labelId int8, name int8, sortName int8, type varchar(255), primary key (id))
create table language (frequency int8, isoCode1 varchar(2), isoCode2b varchar(3), isoCode2t varchar(3), isoCode3 varchar(3), language_id int8, id int8 not null, primary key (id))
create table locale_base (table_id VARCHAR NOT NULL not null, id int8 not null, locale VARCHAR NOT NULL, primary key (id))
create table long_count_base (table_id VARCHAR NOT NULL not null, id int8 not null, count int8 not null, primary key (id))
create table long_id_gid_name (gid varchar(50) not null, id int8 not null, primary key (id))
create table long_id_name (id int8 not null, name VARCHAR NOT NULL not null, primary key (id))
create table long_lengthy_base (table_id VARCHAR NOT NULL not null, id int8 not null, length int8 not null, track_id int8 not null, primary key (id))
create table long_number_base (table_id VARCHAR NOT NULL not null, id int8 not null, number int8 not null, primary key (id))
create table long_position_base (table_id VARCHAR NOT NULL not null, id int8 not null, position int8 not null, primary key (id))
create table Medium (medium_id int8, id int8 not null, medium_format_id int8 not null, release_id int8 not null, primary key (id))
create table medium_count_join (position_id int8 not null, medium_id int8 not null, primary key (medium_id))
create table medium_format (id int8 not null, description varchar(4000), gid varchar(50) not null, hasDiscId varchar(6) not null, name varchar(100) not null, parent int8, year int4, primary key (id))
create table medium_position_join (position_id int8 not null, medium_id int8 not null, primary key (medium_id))
create table recording (recording_id int8 not null, id int8 not null, artist_credit_id int8 not null, recordingLenght_id int8, primary key (id))
create table recording_alias (id int8 not null, begin_date_day int4, begin_date_month int4, begin_date_year int4, end_date_day int4, end_date_month int4, end_date_year int4, locale varchar(1000), name varchar(1000) not null, sortName varchar(1000) not null, recording_id int8 not null, recording_type_id int8, primary key (id))
create table RecordingAliasType (id int8 not null, primary key (id))
create table release (release_id int8 not null, id int8 not null, artist_credit_id int8, language_id int8, packaging_id int8, release_group_id int8 not null, status_id int8, primary key (id))
create table release_alias (relase_alias_id int8 not null, id int8 not null, release_id int8 not null, type_id int8 not null, primary key (id))
create table release_alias_begin_date_join (date_id int8, release_alias_id int8 not null, primary key (release_alias_id))
create table release_alias_end_date_join (date_id int8, release_alias_id int8 not null, primary key (release_alias_id))
create table release_alias_locale_join (locale_id int8, release_alias_id int8 not null, primary key (release_alias_id))
create table release_alias_sortname_join (sortname_id int8 not null, release_alias_id int8 not null, primary key (release_alias_id))
create table release_barcode_join (barcode_id int8, release_id int8 not null, primary key (release_id))
create table release_comment_join (barcode_id int8, release_id int8 not null, primary key (release_id))
create table release_group (release_group_id int8 not null, id int8 not null, artist_credit_id int8 not null, type_id int8, primary key (id))
create table release_group_primary_type (id int8 not null, gid varchar(50) not null, name varchar(255) not null, primary key (id))
create table release_packaging (release_packaging_id int8 not null, id int8 not null, primary key (id))
create table release_status (release_status_id int8 not null, id int8 not null, primary key (id))
create table ReleaseAliasType (id int8 not null, primary key (id))
create table sort_name_base (table_id VARCHAR NOT NULL not null, id int8 not null, sort_name VARCHAR NOT NULL not null, primary key (id))
create table string_number_base (table_id VARCHAR NOT NULL not null, id int8 not null, number VARCHAR NOT NULL, primary key (id))
create table track (track_id int8, id int8 not null, artistCredit_id int8 not null, medium_id int8 not null, recordingId int8 not null, primary key (id))
create table track_length_join (length_id int8 not null, track_id int8 not null, primary key (track_id))
create table track_number_join (number_id int8 not null, track_id int8 not null, primary key (track_id))
create table track_position_join (position_id int8, track_id int8 not null, primary key (track_id))
alter table area add constraint uk_area_id unique (area_id)
alter table artist add constraint uk_artist_id unique (artist_id)
alter table artist_alias add constraint uk_artist_alias_id unique (artist_alias_id)
alter table artist_alias_sortname_join add constraint UK_pj758kjmvfi4w6iylmqqyf87y unique (sort_name_id)
alter table artist_credit add constraint uk_artist_credit_id unique (artist_credit_id)
alter table artist_credit_count_join add constraint UK_b1e6iatyc0a9ebcsnt02cmy6v unique (artist_count_id)
alter table artist_ref_count_join add constraint UK_cjp45m8pm8reo0nj52n626alp unique (artist_refcount_id)
alter table artist_sortname_join add constraint UK_tcbnii28trkvjw0for5lvukji unique (sort_name_id)
create index bar_code_idx on bar_code_base (bar_code)
create index base_type_id_idx on base_type (type_id)
create index comment_table_idx on comment_base (comment)
create index date_year_idx on composite_dates (year)
create index description_table_idx on description_base (description)
create index description_table_id_idx on description_base (table_id)
alter table instrument add constraint uk_instrument_id unique (instrument_id)
alter table InstrumentType_instrument add constraint UK_dgf73kj1tm86puuhtoatiqqkk unique (instruments_id)
alter table language add constraint uk_language_id unique (language_id)
create index locale_table_idx on locale_base (locale)
create index long_count_field_idx on long_count_base (count)
alter table long_id_gid_name add constraint uk_id_gid_name unique (gid)
create index name_idx on long_id_name (name)
create index long_length_table_idx on long_lengthy_base (length)
alter table long_lengthy_base add constraint UK_ns2jmpxdohnw34m6eqpj9q5ny unique (track_id)
create index long_position_position_idx on long_position_base (position)
create index long_position_table_idx on long_position_base (table_id)
alter table Medium add constraint uk_medium_id unique (medium_id)
alter table medium_count_join add constraint UK_r7raky87e1ui5csktub3ecdco unique (position_id)
alter table recording add constraint uk_recording_id unique (recording_id)
alter table release add constraint uk_release_id unique (release_id)
alter table release_group add constraint uk_release_group_id unique (release_group_id)
alter table release_packaging add constraint uk_release_pack_id unique (release_packaging_id)
alter table release_status add constraint uk_release_status_id unique (release_status_id)
create index sort_name_idx on sort_name_base (sort_name)
create index string_number_number_idx on string_number_base (number)
alter table track_length_join add constraint UK_scyko933yrk07ctctpybraddq unique (length_id)
alter table track_number_join add constraint UK_fnkrp16hmvam21nqghfptddt5 unique (number_id)
alter table area add constraint FK36ijraeico3lyy6mtk1sqpi8t foreign key (type_id) references AreaType
alter table area add constraint FK1bgs2s8cvrbmh8sn23l16vj0q foreign key (id) references long_id_gid_name
alter table area_begin_date_join add constraint FKp4uqnvhavqhyyatk5dfrieo6j foreign key (date_id) references composite_dates
alter table area_begin_date_join add constraint FKhxwhfi68h7pm6nw99q60kulwx foreign key (area_id) references area
alter table area_comment_join add constraint FKht1gjphb8hqa33q1rjnh1uyy4 foreign key (comment_id) references comment_base
alter table area_comment_join add constraint FKifb9goxbil6mp8qnlc652fg1o foreign key (area_id) references area
alter table area_end_date_join add constraint FKs9pkgex6hjbjjvognm0jvyve foreign key (date_id) references composite_dates
alter table area_end_date_join add constraint FK2to1rsv7q6jo3oh51adue24sq foreign key (area_id) references area
alter table AreaType add constraint FKr4535o1vubuc9s4gdtcpljl3r foreign key (id) references base_type
alter table artist add constraint FKjft6k91ihgppugrvdu4rb3qe2 foreign key (type_id) references ArtistType
alter table artist add constraint FK5j7iyhq5y0u5bkj6c8i5b3idg foreign key (id) references long_id_gid_name
alter table artist_alias add constraint FKdfpdahx1espsoktxbv5toco1b foreign key (id) references long_id_name
alter table artist_alias_begin_date_join add constraint FKr5yh1fq8o1vaaiaosk1okb3ab foreign key (date_id) references composite_dates
alter table artist_alias_begin_date_join add constraint FKpks05cos5wm1vmqr2pyjt97sf foreign key (artist_alias_id) references artist_alias
alter table artist_alias_end_date_join add constraint FKm5c36tqplh8bwp1qrw0phxv0i foreign key (date_id) references composite_dates
alter table artist_alias_end_date_join add constraint FKmug4f2wcro7htgp48a4iwycu8 foreign key (artist_alias_id) references artist_alias
alter table artist_alias_join add constraint FKgcd77qcx31282qmxeaypm2wl1 foreign key (artist_id) references artist
alter table artist_alias_join add constraint FKjjqk70jqobun8g8ywh9twu8d2 foreign key (artist_alias_id) references artist_alias
alter table artist_alias_locale_join add constraint FKb6xi7sb9l8h80v4qdb52gewf2 foreign key (locale_id) references locale_base
alter table artist_alias_locale_join add constraint FKtle5378eltgllln6n2jimbrrd foreign key (artist_alias_id) references artist_alias
alter table artist_alias_sortname_join add constraint FKtnhfe96x3gvwohh93u88harun foreign key (sort_name_id) references sort_name_base
alter table artist_alias_sortname_join add constraint FKaljjsn3xplko82gxwoyvnhhdg foreign key (artist_alias_id) references artist_alias
alter table artist_alias_type_join add constraint FK61kxfefqhh2u14nev0mq7e2rl foreign key (alias_type_id) references ArtistAliasType
alter table artist_alias_type_join add constraint FKp27ue68w73pcnl22m8cmvopjk foreign key (artist_alias_id) references artist_alias
alter table artist_area_join add constraint FK5v2pynxsg5phk81a9a888f8p6 foreign key (area_id) references area
alter table artist_area_join add constraint FKjwws3jf2jrltwhscmyr4c2c8d foreign key (artist_id) references artist
alter table artist_begin_area_join add constraint FKlib6g7ixl78iper28hwyp4b6j foreign key (area_id) references area
alter table artist_begin_area_join add constraint FKdsqb47nv5mkfer33g0uwjxrg3 foreign key (artist_id) references artist
alter table artist_begin_date_join add constraint FKr4e3bw96hbaq4qopldq91owtr foreign key (date_id) references composite_dates
alter table artist_begin_date_join add constraint FK9ep35foih1qqx5cvjt9lwa1gy foreign key (artist_id) references artist
alter table artist_comment_join add constraint FK5ny27souggg2m4ovikg8ii5jn foreign key (comment_id) references comment_base
alter table artist_comment_join add constraint FKtqvsp3t5j61w068n51iowndiw foreign key (artist_id) references artist
alter table artist_credit add constraint FKsfhccwknqcc8klrsbswt1hdsp foreign key (id) references long_id_name
alter table artist_credit_count_join add constraint FKf79dl4q17hswqhv9y4mb65qat foreign key (artist_count_id) references long_count_base
alter table artist_credit_count_join add constraint FK9pp1ahlexg9hgppy4p3eeit7y foreign key (artist_credit_id) references artist_credit
alter table artist_credit_name add constraint FK7js2hdti9oon8qxw0imp1s0ts foreign key (artistid) references artist
alter table artist_credit_name add constraint FKc8rsbsxnwsngtfqha46qhjlfv foreign key (artist_credit_id) references artist_credit
alter table artist_credit_name_rel add constraint FKn3g0jyj028jrwt6ckpuvk1y5j foreign key (artist_name_id) references artist
alter table artist_credit_name_rel add constraint FK44fgvkqt29bcp2ai5oewfl4p1 foreign key (artist_credit_id) references artist_credit
alter table artist_end_area_join add constraint FKdq9xln44yycsfyifwjhpojy5 foreign key (area_id) references area
alter table artist_end_area_join add constraint FKqpmbpcxmsx9horym1828a6nhm foreign key (artist_id) references artist
alter table artist_end_date_join add constraint FK72tv7jmp2dy6ivoegudsuyann foreign key (date_id) references composite_dates
alter table artist_end_date_join add constraint FK9xnh1e0g4gykda5onwvpl96w0 foreign key (artist_id) references artist
alter table artist_gender_join add constraint FKebm6l5hwjy51kowqos8nevry1 foreign key (gender_id) references Gender
alter table artist_gender_join add constraint FKhmr79ir0dvn6l6g2k4asxe9rh foreign key (artist_id) references artist
alter table artist_ref_count_join add constraint FKlb2e7nxkf00c41ynywljxmuiy foreign key (artist_refcount_id) references long_count_base
alter table artist_ref_count_join add constraint FKsug54x25nuu957axm0c4obs0u foreign key (artist_credit_id) references artist_credit
alter table artist_sortname_join add constraint FK56a9947m1dr2c1j6l52plkspq foreign key (sort_name_id) references sort_name_base
alter table artist_sortname_join add constraint FK6q14bw7t5sywulcx7pcu6bjcs foreign key (artist_id) references artist
alter table ArtistAliasType add constraint FKalono78x6746rw2wcmg6t46k2 foreign key (id) references base_type
alter table ArtistType add constraint FKedp2kuqtci95iw7guvt968j3c foreign key (id) references base_type
alter table base_type add constraint FKodn6b3grgeqmw6b2auasx9n4e foreign key (id) references long_id_gid_name
alter table base_type_description_join add constraint FKckvph0x1homuj7hn7wver3f8k foreign key (description_id) references description_base
alter table base_type_description_join add constraint FK44wmj6925gqqena2mnr2d3c0e foreign key (base_type_id) references base_type
alter table description_comment_join add constraint FKgwus7t07ciceehfgxllpj5qbu foreign key (description_id) references description_base
alter table description_comment_join add constraint FK6m3tg6kbamffc1gx8ptughdsg foreign key (instrument_id) references instrument
alter table Gender add constraint FK5lmp1q9iu740xud1gxangsrg8 foreign key (id) references base_type
alter table instrument add constraint FK94bpiu9rbn67ehf5k349ooksf foreign key (type_id) references InstrumentType
alter table instrument add constraint FKj66ki180cgmuttavrsr0etjlw foreign key (id) references long_id_gid_name
alter table instrument_comment_join add constraint FK2jwbt9rug9qg7co42h1n2wrfx foreign key (comment_id) references comment_base
alter table instrument_comment_join add constraint FK8dawlfgg0km0ofqhs7k8i10rb foreign key (instrument_id) references instrument
alter table InstrumentType add constraint FKifirbapdceb91xqv0vibhd3vq foreign key (id) references base_type
alter table InstrumentType_instrument add constraint FKktv0l7uftgcf6ncyjxx7w6rjq foreign key (instruments_id) references instrument
alter table InstrumentType_instrument add constraint FK9kufqmeecpl0ho81sffyn9y8d foreign key (InstrumentType_id) references InstrumentType
alter table language add constraint FKtb4qe7sw6ccf7pwoj11hfslx2 foreign key (id) references long_id_name
alter table long_id_gid_name add constraint FKse71nfag7h861smx8ju4becl9 foreign key (id) references long_id_name
alter table long_lengthy_base add constraint FKeoqyoydaygg3uc0uflpedqpjb foreign key (track_id) references track
alter table Medium add constraint FK88xlaa32r1xxpm9vykwh3675e foreign key (medium_format_id) references medium_format
alter table Medium add constraint FKkhs58g5n5bnyqr14hklutr1jc foreign key (release_id) references release
alter table Medium add constraint FKd29oftthn2kc1h93yvak4o8oh foreign key (id) references long_id_name
alter table medium_count_join add constraint FKnb6vfdqw575q0c53w4c43bae1 foreign key (position_id) references long_count_base
alter table medium_count_join add constraint FK5gnqnfnwrqj7uuio47742wsqt foreign key (medium_id) references Medium
alter table medium_position_join add constraint FK7npic7e0xpkw01dji0v59jimt foreign key (position_id) references long_position_base
alter table medium_position_join add constraint FKqpxdjbhc5y2t6sb9g0d6sswg2 foreign key (medium_id) references Medium
alter table recording add constraint FKwhnq53y2fteeexfpar6558ut foreign key (artist_credit_id) references artist_credit
alter table recording add constraint FKtdh05j1q5nmsl9q7mo77gtssp foreign key (recordingLenght_id) references long_lengthy_base
alter table recording add constraint FKqflt50nekymxmtee3kj9ax9t7 foreign key (id) references long_id_gid_name
alter table recording_alias add constraint FKf3bf0jihug16pfd3n6fksolla foreign key (recording_id) references recording
alter table recording_alias add constraint FKhxhbaha10333uu0e1o6fs02bw foreign key (recording_type_id) references RecordingAliasType
alter table RecordingAliasType add constraint FKgi0bcnu4b95rbnovoat59xglc foreign key (id) references base_type
alter table release add constraint FKrjxedvgqei5yg4s02538jeu55 foreign key (artist_credit_id) references artist_credit
alter table release add constraint FKne7hflamxfjrj3nwcp7rmyqv1 foreign key (language_id) references language
alter table release add constraint FKevsgr1j8wgbldkpylyaaruorq foreign key (packaging_id) references release_packaging
alter table release add constraint FKrdc218r3ti4ecmiwm79k8nrm5 foreign key (release_group_id) references release_group
alter table release add constraint FKqrik1raq7rgbf262jxyuy9m5f foreign key (status_id) references release_status
alter table release add constraint FKphss186b3hrmhvp3yxmht6wym foreign key (id) references long_id_gid_name
alter table release_alias add constraint FK8h783sk9tygkr28w7uaw66hgs foreign key (release_id) references release
alter table release_alias add constraint FK2t0eyhwmmxyhavv0vfc52tsn0 foreign key (type_id) references ReleaseAliasType
alter table release_alias add constraint FKjuo57gcm1hs47dbu7xxh2cqys foreign key (id) references long_id_name
alter table release_alias_begin_date_join add constraint FKcwclxi1f7kynv6aqi09b7k0o4 foreign key (date_id) references composite_dates
alter table release_alias_begin_date_join add constraint FK8fwb728l8xka6plxvd5ioam9v foreign key (release_alias_id) references release_alias
alter table release_alias_end_date_join add constraint FKgk7ci5htcryi5ldvy54kw1p5j foreign key (date_id) references composite_dates
alter table release_alias_end_date_join add constraint FKh23905t8qli78gtd54skm2k32 foreign key (release_alias_id) references release_alias
alter table release_alias_locale_join add constraint FKfu08i10orqtp60yubiuxco5ug foreign key (locale_id) references locale_base
alter table release_alias_locale_join add constraint FKfi3muh2yf67v2e3aavs7o23iy foreign key (release_alias_id) references release_alias
alter table release_alias_sortname_join add constraint FKk0b8uym87fp1se4cwbp8efudx foreign key (sortname_id) references sort_name_base
alter table release_alias_sortname_join add constraint FK47aqy9lhj7y73lnygfx8i5wbv foreign key (release_alias_id) references release_alias
alter table release_barcode_join add constraint FK11ycaqp6hwmm3vvel4pei3hb3 foreign key (barcode_id) references bar_code_base
alter table release_barcode_join add constraint FK1ohu8rea5r4bflvjfo52wuiak foreign key (release_id) references release
alter table release_comment_join add constraint FKcedh69ea15454jb8hfy2pktyw foreign key (barcode_id) references comment_base
alter table release_comment_join add constraint FKaso5up8i49s07ak8mqb1w13bo foreign key (release_id) references release
alter table release_group add constraint FKjhugaa7xl4008xcscrx21v8iw foreign key (artist_credit_id) references artist_credit
alter table release_group add constraint FKngm4tpa9mbjtosws33t7pryhy foreign key (type_id) references release_group_primary_type
alter table release_group add constraint FKch1pyhl900l49xn17d9ff5dym foreign key (id) references long_id_gid_name
alter table release_packaging add constraint FKth87rq87hbka9fr5d380k0bvj foreign key (id) references long_id_gid_name
alter table release_status add constraint FK8269hgk917whc82pabbshqnqk foreign key (id) references long_id_gid_name
alter table ReleaseAliasType add constraint FKpn1yuiiipoklqkwsfv8ysdptd foreign key (id) references base_type
alter table track add constraint FKir00mv1qv09apd7emsparvh9n foreign key (artistCredit_id) references artist_credit
alter table track add constraint FKqxta8bmt9en1errp76u3m2oau foreign key (medium_id) references Medium
alter table track add constraint FKcbmq3cor34r184u84jtfhiw1r foreign key (recordingId) references recording
alter table track add constraint FKj3gkj9wkfhjhi8eh1tf8vsgdb foreign key (id) references long_id_gid_name
alter table track_length_join add constraint FK2jl5eyq1dencmw90asa39hhp foreign key (length_id) references long_lengthy_base
alter table track_length_join add constraint FK3aj1gvdufrwb9snay9cy91t53 foreign key (track_id) references track
alter table track_number_join add constraint FKmwwixblea4b54nrbc2i3ppbut foreign key (number_id) references string_number_base
alter table track_number_join add constraint FKjfj36ofn0l3no50vutks0ha5x foreign key (track_id) references track
alter table track_position_join add constraint FKf2dsts4egfuibtotwmjbmf3tn foreign key (position_id) references long_position_base
alter table track_position_join add constraint FKptd32j597vii6l8900688trkt foreign key (track_id) references track
