databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1475606163417-1") {
		addColumn(tableName: "procesador_de_poliza") {
			column(name: "orden", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}
}
