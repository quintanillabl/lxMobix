databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1438952290201-1") {
		addNotNullConstraint(columnDataType: "varchar(20)", columnName: "serie", tableName: "folio")
	}
}
