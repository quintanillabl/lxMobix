databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1444314744808-1") {
		modifyDataType(columnName: "fecha", newDataType: "date", tableName: "poliza")
	}

	changeSet(author: "rcancino (generated)", id: "1444314744808-2") {
		addNotNullConstraint(columnDataType: "date", columnName: "fecha", tableName: "poliza")
	}

	changeSet(author: "rcancino (generated)", id: "1444314744808-3") {
		dropIndex(indexName: "unique_folio", tableName: "poliza")
	}

	changeSet(author: "rcancino (generated)", id: "1444314744808-4") {
		createIndex(indexName: "unique_folio", tableName: "poliza", unique: "true") {
			column(name: "sub_tipo")

			column(name: "tipo")

			column(name: "mes")

			column(name: "ejercicio")

			column(name: "empresa_id")

			column(name: "folio")
		}
	}
}
