databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "POLDET68855-1") {
		modifyDataType(columnName: "concepto", newDataType: "varchar(255)", tableName: "poliza_det")
		
	}
}
