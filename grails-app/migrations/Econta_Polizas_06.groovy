databaseChangeLog = {
	changeSet(author: "rcancino (generated)", id: "MANUAL_MODA001-01") {
		modifyDataType(columnName: "descripcion", newDataType: "varchar(255)", tableName: "poliza_det")
	}
}
