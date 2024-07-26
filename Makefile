mkdocs:
	@docker build --no-cache -f Dockerfile.docs -t mkdocs-fiap-3soat-g15-healthmed:latest .

docs: mkdocs
	@docker run --rm -v ${CURDIR}:/usr/src/app mkdocs-fiap-3soat-g15-healthmed:latest build --clean

website: mkdocs
	@docker run --rm -v ${CURDIR}:/usr/src/app -p 8000:8000 mkdocs-fiap-3soat-g15-healthmed:latest serve

.PHONY: mkdocs docs website
