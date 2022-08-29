def mavenbuild() {
   echo "Building the package"
   sh "mvn -D SkipTests clean package"
}


def mavensonarbuild() {
   withSonarQubeEnv('sonar') {
      def BRANCH_NAME = utils.getbranch()
   bat "mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install org.jacoco:jacoco-maven-plugin:report"   
   bat "mvn sonar:sonar"
   }
}

def buildDockerTaggedImage(Registry, RegistryRepo, Dockerfile, Tag = BUILD_NUMBER) {
   def imageURI = utils.getImageURI(Registry, RegistryRepo, Tag)
   dockerImage = docker.build(imageURI, "-f $Dockerfile .")
   return dockerImage
}


def publishDockerImage(Registry, RegistryCreds, dockerImage, dockerImageTag, removeAfterPublish = true) {
   docker.withRegistry( 'https://' + Registry, RegistryCreds){
      dockerImage.push()
   }

   if (removeAfterPublish) {
      echo "Removing Doker Image"
      removeDockerImage(dockerImageTag)
   }
}
