databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1436915777735-1") {
		createIndex(indexName: "uuid_uniq_1436915777119", tableName: "cuenta_por_pagar", unique: "true") {
			column(name: "uuid")
		}
	}
}
