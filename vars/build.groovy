def mavenbuild() {
   withSonanrQubeEnv('sonar') {
      def BRANCH_NAME = albutils.getbranch()
   bat "mvn sonar:sonar -Dsonar.branch.name=${BRANCH_NAME}"
   }
}