databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1440719246251-1") {
		addColumn(tableName: "movimiento_de_cuenta") {
			column(name: "a_favor", type: "varchar(255)")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1440719246251-2") {
		addColumn(tableName: "requisicion") {
			column(name: "a_favor", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}
}
