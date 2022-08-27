def mavenbuild() {
   echo "Building the package"
   sh "mvn -D SkipTests clean package"
}


def mavensonarbuild() {
   withSonarQubeEnv('sonar') {
      def BRANCH_NAME = utils.getbranch()
   sh "mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install org.jacoco:jacoco-maven-plugin:report"   
   sh "mvn sonar:sonar -Dsonar.branch.name=${BRANCH_NAME}"
   }
}

def buildDockerTaggedImage(Registry, RegistryRepo, Dockerfile, Tag) {
   def imageURI = utils.getImageURI(Registry, RegistryRepo, Tag)

   dockerImage = docker.build(imageURI, "-f $Dockerfile .")
   return dockerImage
}
