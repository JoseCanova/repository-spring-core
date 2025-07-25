package snippet;

public class Snippet {
	# ... (your existing server, http, management, logging, spring, entitymanager configs) ...
	
	nanotek:
	  csv-configs:
	    artist:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: artist
	      immutable: org.nanotek.beans.csv.ArtistBean
	      baseMap:
	        artistId: 0
	        gid: 1
	        artistName: 2
	        sortName: 3
	        beginDateYear: 4
	        beginDateMonth: 5
	        beginDateDay: 6
	        endDateYear: 7
	        endDateMonth: 8
	        endDateDay: 9
	        typeId: 10
	        areaId: 11
	        genderId: 12
	        comment: 13
	        editsPending: 14
	        lastUpdated: 15
	        ended: 16
	        beginAreaId: 17
	        endAreaId: 18
	    area:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: area
	      immutable: org.nanotek.beans.csv.AreaBean
	      baseMap:
	        areaId: 0
	        gid: 1
	        areaName: 2
	        typeId: 3
	        editsPending: 4
	        lastUpdated: 5
	        beginYear: 6
	        beginMonth: 7
	        beginDay: 8
	        endYear: 9
	        endMonth: 10
	        endDay: 11
	        ended: 12
	    areatype:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: area_type
	      immutable: org.nanotek.beans.csv.AreaTypeBean
	      baseMap:
	        typeId: 0
	        typeName: 1
	        parent: 2
	        childOrder: 3
	        description: 4
	        gid: 5
	    artistaliastype:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: artist_alias_type
	      immutable: org.nanotek.beans.csv.ArtistAliasTypeBean
	      baseMap:
	        typeId: 0
	        typeName: 1
	        parent: 2
	        childOrder: 3
	        description: 4
	        gid: 5
	    artistalias:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: artist_alias
	      immutable: org.nanotek.beans.csv.ArtistAliasBean
	      baseMap:
	        aliasId: 0
	        artistId: 1
	        artistAliasName: 2
	        locale: 3
	        editsPending: 4
	        lastUpdated: 5
	        typeId: 6
	        sortName: 7
	        beginYear: 8
	        beginMonth: 9
	        beginDay: 10
	        endYear: 11
	        endMonth: 12
	        endDay: 13
	        primaryForLocale: 14
	        ended: 15
	    artistcredit:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump/artistcredit
	      fileName: artist_credit
	      immutable: org.nanotek.beans.csv.ArtistCreditBean
	      baseMap:
	        artistCreditId: 0
	        artistCreditName: 1
	        count: 2
	        refCount: 3
	        created: 4
	        editsPending: 5
	    artistcreditedname:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: artist_credit_name
	      immutable: org.nanotek.beans.csv.ArtistCreditedNameBean
	      baseMap:
	        artistCreditId: 0
	        position: 1
	        artistId: 2
	        artistCreditedName: 3
	        artistCreditedNameJoinPhrase: 4
	    releasepackaging:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: release_packaging
	      immutable: org.nanotek.beans.csv.ReleasePackagingBean
	      baseMap:
	        releasePackagingId: 0
	        releasePackagingName: 1
	        parent: 2
	        childOrder: 3
	        description: 4
	        gid: 5
	    releasegroupprimarytype:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: release_group_primary_type
	      immutable: org.nanotek.beans.csv.ReleaseGroupPrimaryTypeBean
	      baseMap:
	        typeId: 0
	        typeName: 1
	        parent: 2
	        childOrder: 3
	        description: 4
	        gid: 5
	    labeltype:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: label_type
	      immutable: org.nanotek.beans.csv.LabelTypeBean
	      baseMap:
	        typeId: 0
	        typeName: 1
	        parent: 2
	        childOrder: 3
	        description: 4
	        gid: 5
	    instrumenttype:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: instrument_type
	      immutable: org.nanotek.beans.csv.InstrumentTypeBean
	      baseMap:
	        typeId: 0
	        typeName: 1
	        parent: 2
	        childOrder: 3
	        description: 4
	        gid: 5
	    gendertype:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: gender
	      immutable: org.nanotek.beans.csv.GenderBean
	      baseMap:
	        typeId: 0
	        typeName: 1
	        parent: 2
	        childOrder: 3
	        description: 4
	        gid: 5
	    artisttype:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: artist_type
	      immutable: org.nanotek.beans.csv.ArtistTypeBean
	      baseMap:
	        typeId: 0
	        typeName: 1
	        parent: 2
	        childOrder: 3
	        description: 4
	        gid: 5
	    recordingaliastype:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: recording_alias_type
	      immutable: org.nanotek.beans.csv.RecordingAliasTypeBean
	      baseMap:
	        typeId: 0
	        typeName: 1
	        parent: 2
	        childOrder: 3
	        description: 4
	        gid: 5
	    releasealiastype:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: release_alias_type
	      immutable: org.nanotek.beans.csv.ReleaseAliasTypeBean
	      baseMap:
	        typeId: 0
	        typeName: 1
	        parent: 2
	        childOrder: 3
	        description: 4
	        gid: 5
	    worktype:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: work_type
	      immutable: org.nanotek.beans.csv.WorkTypeBean
	      baseMap:
	        typeId: 0
	        typeName: 1
	        parent: 2
	        childOrder: 3
	        description: 4
	        gid: 5
	    instrument:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: instrument
	      immutable: org.nanotek.beans.csv.InstrumentBean
	      baseMap:
	        instrumentId: 0
	        gid: 1
	        instrumentName: 2
	        typeId: 3
	        editsPending: 4
	        lastUpdatead: 5
	        comment: 6
	        description: 7
	    mediumformat:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: medium_format
	      immutable: org.nanotek.beans.csv.MediumFormatBean
	      baseMap:
	        mediumFormatId: 0
	        mediumFormatName: 1
	        parent: 2
	        childOrder: 3
	        year: 4
	        discId: 5
	        description: 6
	        gid: 7
	    mediumbean:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: medium
	      immutable: org.nanotek.beans.csv.MediumBean
	      baseMap:
	        mediumId: 0
	        releaseId: 1
	        position: 2
	        mediumFormatId: 3
	        mediumName: 4
	        editsPending: 5
	        lastUpdated: 6
	        count: 7
	    recordingbean:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump/recording_split
	      fileName: recaj
	      immutable: org.nanotek.beans.csv.RecordingBean
	      baseMap:
	        recordingId: 0
	        gid: 1
	        recordingName: 2
	        artistCreditId: 3
	        length: 4
	        comment: 5
	        editsPending: 6
	        lastUpdated: 7
	        video: 8
	    language:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: language
	      immutable: org.nanotek.beans.csv.LanguageBean
	      baseMap:
	        languageId: 0
	        isoCode2T: 1
	        isoCode2B: 2
	        isoCode1: 3
	        languageName: 4
	        frequency: 5
	        isoCode3: 6
	    isrcbean:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: isrc
	      immutable: org.nanotek.beans.csv.IsrcBean
	      baseMap:
	        isrcId: 0
	        recordingId: 1
	        isrc: 2
	        isrcSource: 3
	        editsPending: 4
	        created: 5
	    labelbean:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: label
	      immutable: org.nanotek.beans.csv.LabelBean
	      baseMap:
	        labelId: 0
	        gid: 1
	        labelName: 2
	        beginYear: 3
	        beginMonth: 4
	        beginDay: 5
	        endYear: 6
	        endMonth: 7
	        endDay: 8
	        labelCode: 9
	        labelTypeId: 10
	        areaId: 11
	    releasegroup:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: release_group
	      immutable: org.nanotek.beans.csv.ReleaseGroupBean
	      baseMap:
	        releaseGroupId: 0
	        gid: 1
	        releaseGroupName: 2
	        artistCreditId: 3
	        typeId: 4
	        comment: 5
	        editsPending: 6
	        lastUpdated: 7
	    releasestatus:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: release_status
	      immutable: org.nanotek.beans.csv.ReleaseStatusBean
	      baseMap:
	        releaseStatusId: 0
	        releaseStatusName: 1
	        parent: 2
	        childOrder: 3
	        description: 4
	        gid: 5
	    releasebean:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: release
	      immutable: org.nanotek.beans.csv.ReleaseBean
	      baseMap:
	        releaseId: 0
	        gid: 1
	        releaseName: 2
	        artistCreditId: 3
	        releaseGroupId: 4
	        releaseStatusId: 5
	        releasePackagingId: 6
	        languageId: 7
	        script: 8
	        barCode: 9
	        comment: 10
	        editsPending: 11
	        quality: 12
	        lastUpdated: 13
	    releaselabelbean:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: release_label
	      immutable: org.nanotek.beans.csv.ReleaseLabelBean
	      baseMap:
	        releaseLabelId: 0
	        releaseId: 1
	        labelId: 2
	        releaseLabelNumber: 3
	        lastUpdated: 4
	    workbean:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: work
	      immutable: org.nanotek.beans.csv.WorkBean
	      baseMap:
	        workId: 0
	        gid: 1
	        workName: 2
	        typeId: 3
	        comment: 4
	        pending: 5
	        updated: 6
	    releasealiasbean:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: release_alias
	      immutable: org.nanotek.beans.csv.ReleaseAliasBean
	      baseMap:
	        releaseAliasId: 0
	        releaseId: 1
	        releaseAliasName: 2
	        locale: 3
	        pending: 4
	        updated: 5
	        typeId: 6
	        sortName: 7
	        beginYear: 8
	        beginMonth: 9
	        beginDay: 10
	        endYear: 11
	        endMonth: 12
	        endDay: 13
	        primaryLocale: 14
	        ended: 15
	    trackbean:
	      fileLocation: /mnt/wwn-part2/musicbrainz/mbdump
	      fileName: track
	      immutable: org.nanotek.beans.csv.TrackBean
	      baseMap:
	        trackId: 0
	        gid: 1
	        recordingId: 2
	        mediumId: 3
	        position: 4
	        number: 5
	        trackName: 6
	        artistCreditId: 7
	        length: 8
	        editsPending: 9
	        lastUpdate: 10
	        isDataTrack: 11
}

