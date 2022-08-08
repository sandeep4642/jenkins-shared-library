def mavenbuild() {
   withSonarQubeEnv('sonar') {
      def BRANCH_NAME = utils.getbranch()
   bat "mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install org.jacoco:jacoco-maven-plugin:report"   
   bat "mvn sonar:sonar -Dsonar.branch.name=${BRANCH_NAME}"
   }
}