def mavenbuild() {
   echo "Building the package"
   sh "mvn -D SkipTests clean package"
}


def mavensonarbuild() {
   withSonarQubeEnv('local-sonar1') {
      def BRANCH_NAME = utils.getbranch()
   sh "mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install org.jacoco:jacoco-maven-plugin:report"   
   sh "mvn sonar:sonar"
   }
}

def windowsmavensonarbuild() {
   withSonarQubeEnv('local-sonar1') {
      def BRANCH_NAME = utils.getbranch()
   bat "mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install org.jacoco:jacoco-maven-plugin:report"   
   bat "mvn sonar:sonar"
   }
}

def qualitygate() {
   sleep(30)
   echo "Checking quality gate status"
   waitForQualityGate abortPipeline: true
}

def buildDockerTaggedImage(Registry, RegistryRepo, Dockerfile, Tag = BUILD_NUMBER) {
   def imageURI = utils.getImageURI(Registry, RegistryRepo, Tag)
   dockerImage = docker.build(imageURI, "-f $Dockerfile .")
   return dockerImage
}

def buildDockerTaggedImage1(imageURI) {
   dockerImage = docker.build("$imageURI", "-f $Dockerfile .")
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
