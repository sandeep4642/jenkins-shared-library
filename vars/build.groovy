def mavenbuild() {
   bat "mvn sonar:sonar -Dsonar.branch.name=${BRANCH_NAME}"
}