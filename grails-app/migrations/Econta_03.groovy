databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1445453062727-1") {
		addColumn(tableName: "gasto_det") {
			column(name: "concepto", type: "varchar(50)")
		}
	}
}
