module.exports = function (grunt) {

    grunt.initConfig({
        jshint: {
            options: {
                esversion: 6,
                bitwise: true,
                curly: true,
                asi: false
            },
            all: ['app/js/**/*.js', '!app/js/assets/**/*.js']
        },
        concat: {
            dist: {
                src: ['app/js/**/*.js', '!app/js/assets/**/*.js'],
                dest: 'dist/js/build.min.js'
            }
        },
        uglify: {
            dist: {
                src: ['dist/js/build.min.js'],
                dest: 'dist/js/build.min.js'
            }
        },
        cssmin: {
            dist: {
                src: ['app/css/**/*.css'],
                dest: 'dist/css/style.min.css'
            }
        },
        htmlmin: {
            dist: {
                expand: true,
                options: {
                    removeComments: true,
                    collapseWhitespace: true
                },
                src: ['app/pages/**/*.html', 'index.html'],
                dest: 'dist'
            }
        },
        copy: {
            dist: {
                expand: true,
                src: [
                    'node_modules/angular/angular.min.js', 
                    'node_modules/angular-route/angular-route.min.js', 
                    'node_modules/angular-animate/angular-animate.min.js',
                    'node_modules/bootstrap/dist/css/bootstrap.min.css',
                    'node_modules/bootstrap/dist/js/bootstrap.min.js',
                    'node_modules/jquery/dist/jquery.min.js',
                    'node_modules/highcharts/highcharts.js',
                    'node_modules/bootstrap-growl-ifightcrime/jquery.bootstrap-growl.min.js',
                    'app/js/assets/popper.mim.js',
                    'app/js/assets/jQueryTabs.js',
                    'app/js/assets/jquery-1.9.0.min.js',
                    'node_modules/angular-material/angular-material.js'
                ],
                dest: 'dist'
            },
            dev: {
                expand: true,
                src: [
                    'node_modules/angular/angular.min.js', 
                    'node_modules/angular-route/angular-route.min.js', 
                    'node_modules/angular-animate/angular-animate.min.js',  
                    'node_modules/bootstrap/dist/css/bootstrap.min.css',
                    'node_modules/bootstrap/dist/js/bootstrap.min.js',
                    'node_modules/jquery/dist/jquery.min.js',
                    'node_modules/bootstrap-growl-ifightcrime/jquery.bootstrap-growl.min.js',
                    'node_modules/highcharts/highcharts.js',
                    'app/**',
                    'index.html',
                    'app/js/assets/jQueryTabs.js',
                    'app/js/assets/jquery-1.9.0.min.js',
                    'node_modules/angular-material/angular-material.js'
                ],
                dest: 'dist'
            }
        },
        clean: {
            dist: {
                src: ['dist']
            }
        },
        connect: {
            server: {
                options: {
                    port: 8000,
                    base: './dist'
                }
            }
        },
        watch: {
            dev: {
                files:  ['app/**/*.js', 'app/**/*.html', 'app/**/*.css', 'index.html'],
                tasks: ['refresh'],
                options: {
                    spawn: false,
                    livereload: true
                }
            },
            dist: {
                files:  ['app/**/*.js', 'app/**/*.html', 'app/**/*.css', 'index.html'],
                options: {
                    spawn: false,
                    livereload: true
                }
            }
        }
        
    });

    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-uglify-es');
    grunt.loadNpmTasks('grunt-contrib-cssmin');
    grunt.loadNpmTasks('grunt-contrib-htmlmin');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-connect');
    grunt.loadNpmTasks('grunt-contrib-watch');

    grunt.registerTask('default', [
        'jshint:all','clean:dist', 'copy:dev', 'connect:server', 'watch:dev']);

    grunt.registerTask('dist', [
        'jshint:all','clean:dist', 'concat:dist', 'uglify:dist', 
        'cssmin:dist', 'htmlmin:dist', 'copy:dist', 'connect:server', 'watch:dist']);

    grunt.registerTask('refresh', [
        'jshint:all', 'clean:dist', 'copy:dev']);

};