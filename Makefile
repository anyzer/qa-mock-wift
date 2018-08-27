#####################################################
# Makefile used for Applications which have there
# build systems.
#####################################################

include Makefile.particulars

package:
	@echo "Building application package..."
	mvn clean package -U
	@echo "Application package built"

build-image:
	@echo "Building image..."
	docker build --no-cache --build-arg "REV=0.1" --rm -t qa-mock-wift .
	@echo "Docker image $(DOCKER_REPOSITORY)$(IMAGE_NAME) built"

.PHONY: \
	version \
	run-test \
	package \
	build-image \
	publish