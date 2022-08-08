def mavenbuild() {
   withSonarQubeEnv('sonar') {
      def BRANCH_NAME = utils.getbranch()
   bat "mvn sonar:sonar -Dsonar.branch.name=${BRANCH_NAME}"
   }
}