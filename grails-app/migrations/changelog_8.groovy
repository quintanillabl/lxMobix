databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1436459579290-1") {
		addColumn(tableName: "cierre_contable") {
			column(name: "creado_por", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436459579290-2") {
		addColumn(tableName: "cierre_contable") {
			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436459579290-3") {
		addColumn(tableName: "cierre_contable") {
			column(name: "ejercicio", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436459579290-4") {
		addColumn(tableName: "cierre_contable") {
			column(name: "empresa_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436459579290-5") {
		addColumn(tableName: "cierre_contable") {
			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436459579290-6") {
		addColumn(tableName: "cierre_contable") {
			column(name: "mes", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436459579290-7") {
		addColumn(tableName: "cierre_contable") {
			column(name: "modificado_por", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436459579290-8") {
		addColumn(tableName: "cierre_contable") {
			column(name: "tipo", type: "varchar(14)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436459579290-10") {
		createIndex(indexName: "FK_7ba17l2d3il0q1figifff3vk8", tableName: "cierre_contable") {
			column(name: "empresa_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436459579290-11") {
		createIndex(indexName: "unique_mes", tableName: "cierre_contable", unique: "true") {
			column(name: "ejercicio")

			column(name: "empresa_id")

			column(name: "mes")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1436459579290-9") {
		addForeignKeyConstraint(baseColumnNames: "empresa_id", baseTableName: "cierre_contable", constraintName: "FK_7ba17l2d3il0q1figifff3vk8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "empresa", referencesUniqueColumn: "false")
	}
}
