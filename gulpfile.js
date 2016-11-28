const gulp = require('gulp')
const browserSync = require('browser-sync')
const reload = browserSync.reload


//BrowserSync
gulp.task('serve', function () {
    browserSync({
        server: {
            baseDir: 'src/main/resources/static'
        }
    })
    gulp.watch(['*.html', 'css/*.css', 'js/*.js'], { cwd: 'src/main/resources/static' }, reload)
})