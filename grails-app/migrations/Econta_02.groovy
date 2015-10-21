databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1445446385258-1") {
		addColumn(tableName: "poliza") {
			column(name: "entidad", type: "varchar(255)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1445446385258-2") {
		addColumn(tableName: "poliza") {
			column(name: "origen", type: "bigint")
		}
	}
}
