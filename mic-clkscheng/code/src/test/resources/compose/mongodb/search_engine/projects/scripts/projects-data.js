db = db.getSiblingDB('search_engine');

db.projects.drop()
db.projects.insertMany([
    {
        name: "Sistema de Gestión de Inventarios",
        description: "Una plataforma para controlar stock en tiempo real.",
        tags: ["java", "spring", "docker"],
        createdAt: new Date()
    },
    {
        name: "E-commerce API",
        description: "Backend para una tienda online con microservicios.",
        tags: ["microservices", "mongodb", "fuzzy-search"],
        createdAt: new Date()
    },
    {
        name: "Portal de Noticias",
        description: "Sitio web con búsqueda avanzada de artículos.",
        tags: ["news", "search", "web"],
        createdAt: new Date()
    }
])

const indexProject = {
    "mappings": {
        "dynamic": true,
        "fields": {
            "name": [
                {
                    "type": "autocomplete"
                },
                {
                    "type": "token"
                }
            ],
            "tags": [
                {
                    "type": "string"
                },
                {
                    "type": "token"
                }
            ]
        }
    }
};
db.projects.createSearchIndex("dyn_idx0", indexProject)

