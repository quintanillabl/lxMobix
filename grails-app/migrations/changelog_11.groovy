databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1436882879386-1") {
		addColumn(tableName: "cfdi") {
			column(name: "comentario", type: "varchar(100)")
		}
	}
}
