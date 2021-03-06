#!/usr/bin/env groovy

def call() {
    
    def exists = fileExists 'git_externals.json'

    echo "Retrieve externals ${exists}"
    
    // Make Git externals optional
    if (exists) {
        // Clear cache folder used by git-externals given it expects empty repositories
        dir (".git_externals") {
            echo "Clear git externals cache folder"
            deleteDir()
        }
        
        sshagent (credentials: [env.GITHUB_CREDENTIAL_ID]) {
            // Want to throw away the noisy output; TODO ${jenkins_private_key} here?
            sh returnStdout:true, script: '''
            git externals update
            git externals foreach git pull
            git externals update
            '''
        }
    }
}
