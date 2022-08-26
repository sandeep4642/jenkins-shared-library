def mavenbuild() {
   echo "Building the package"
   sh 'mvn clean install package'
}


def mavensonarbuild() {
   withSonarQubeEnv('sonar') {
      def BRANCH_NAME = utils.getbranch()
   bat "mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install org.jacoco:jacoco-maven-plugin:report"   
   bat "mvn sonar:sonar -Dsonar.branch.name=${BRANCH_NAME}"
   }
}

def buildDockerTaggedImage(Registry, RegistryRepo, Dockerfile, Tag) {
   def imageURI = utils.getImageURI(Registry, RegistryRepo, Tag)

   dockerImage = docker.build(imageURI, "-f $Dockerfile .")
   return dockerImage
}