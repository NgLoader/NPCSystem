docker.image('3.8.1-openjdk-16').inside {
  git '…your-sources…'
  writeFile file: 'settings.xml', text: "<settings><localRepository>${pwd()}/.m2repo</localRepository></settings>"
  sh 'mvn -B -s settings.xml clean install'
}