def mavenbuild() {
   bat "mvn sonar:sonar -Dsonar.branch.name=${env.BRANCH_NAME}"
}