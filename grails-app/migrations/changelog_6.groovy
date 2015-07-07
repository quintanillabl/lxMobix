databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1436301693661-1") {
		dropIndex(indexName: "UK_7nmch6c0klcv5tjdw37rm20mk", tableName: "cliente")
	}

	changeSet(author: "rcancino (generated)", id: "1436301693661-2") {
		createIndex(indexName: "unique_nombre", tableName: "cliente", unique: "true") {
			column(name: "empresa_id")

			column(name: "nombre")
		}
	}
}
