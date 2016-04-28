var gulp = require('gulp'),
    concat = require('gulp-concat'),
    minifyCSS = require('gulp-minify-css'),
    concatCss = require('gulp-concat-css'),
    uglify = require('gulp-uglify'),
    seajsCombo = require('gulp-seajs-combo');

//逻辑代码打包
(function () {
    var biz = {
        index: {
            js: 'staticfile/src/biz/index/index.js',
            css: 'staticfile/src/biz/index/index.css'
        },
        home: {
            js: 'staticfile/src/biz/home/home.js',
            css: 'staticfile/src/biz/home/home.css'
        },
        home: {
            js: 'staticfile/src/biz/home/home.js',
            css: 'staticfile/src/biz/home/home.css'
        },
        detail: {
            js: 'staticfile/src/biz/detail/detail.js',
            css: 'staticfile/src/biz/detail/detail.css'
        },
        filter: {
            js: 'staticfile/src/biz/filter/filter.js',
            css: 'staticfile/src/biz/filter/filter.css'
        },
        findpsw: {
            js: 'staticfile/src/biz/findpsw/findpsw.js',
            css: 'staticfile/src/biz/findpsw/findpsw.css'
        },
        login: {
            js: 'staticfile/src/biz/login/login.js',
            css: 'staticfile/src/biz/login/login.css'
        },
        officeList: {
            js: 'staticfile/src/biz/officeList/officeList.js',
            css: 'staticfile/src/biz/officeList/officeList.css'
        },
        register: {
            js: 'staticfile/src/biz/register/register.js',
            css: 'staticfile/src/biz/register/register.css'
        },
        release: {
            js: 'staticfile/src/biz/release/release.js',
            css: 'staticfile/src/biz/release/release.css'
        },
        server: {
            js: 'staticfile/src/biz/server/server.js',
            css: 'staticfile/src/biz/server/server.css'
        },
        userinfo: {
            js: 'staticfile/src/biz/userinfo/userinfo.js',
            css: 'staticfile/src/biz/userinfo/userinfo.css'
        },
        
        
    };

    var allBizTask = [];
    var prop = '';

    for (prop in biz) {

        //md，用立即执行函数来解决闭包变量prop。
        /*(function(param){
            gulp.task(param, function() {
                return gulp.src(biz[param].js)
                    .pipe(concat(param + '.js'))
                    .pipe(gulp.dest('staticfile/development'))
                    .pipe(uglify())
                    .pipe(concat(param + '.min.js'))
                    .pipe(gulp.dest('staticfile/production'));
            });
        })(prop);*/
        (function(param){
            gulp.task(param + '_js', function() {
                return gulp.src(biz[param].js)
                    .pipe(seajsCombo())
                    .pipe(gulp.dest('staticfile/development'))
                    .pipe(uglify())
                    .pipe(seajsCombo())
                    .pipe(gulp.dest('staticfile/production'));
            });
            gulp.task(param + '_css', function() {
                return gulp.src(biz[param].css)
                    .pipe(concatCss(param + '.css'))
                    .pipe(gulp.dest('staticfile/development'))
                    .pipe(minifyCSS())
                    .pipe(concat(param + '.css'))
                    .pipe(gulp.dest('staticfile/production'));
            });
            
        })(prop);
        gulp.task(prop, [prop + '_css', prop + '_js']);
        allBizTask.push(prop);
    }

    gulp.task('biz', allBizTask);

})();

//公用js和css打包
(function () {
    var base = {
        js: [
            'staticfile/src/lib/underscore/underscore-min.js',
            'staticfile/src/lib/jquery/jquery-1.11.0.js',
            'staticfile/src/lib/jquery-cookie/jquery.cookie-min.js',
            'staticfile/src/lib/noty/noty.js',
        ],
        css: [
            'staticfile/src/lib/pure/pure.min.css',
            'staticfile/src/lib/pure/pure-apply.css',
            'staticfile/src/common/base/base.css',
            'staticfile/src/lib/font/css/font-awesome.css',
            'staticfile/src/common/footer/footer.css',
            'staticfile/src/common/header/header.css'
        ]
    };

    gulp.task('minify-lib-scripts', function() {
        // Minify and copy all JavaScript (except vendor scripts)
        // with sourcemaps all the way down
        return gulp.src(base.js)
            .pipe(concat('base.js'))
            .pipe(gulp.dest('staticfile/development'))
            .pipe(uglify())
            .pipe(concat('base.js'))
            .pipe(gulp.dest('staticfile/production'));
    });

    gulp.task('minify-lib-css', function() {
        return gulp.src(base.css)
            .pipe(concatCss('base.css'))
            .pipe(gulp.dest('staticfile/development'))
            .pipe(minifyCSS())
            .pipe(concat('base.css'))
            .pipe(gulp.dest('staticfile/production'));
    });

    // The default task (called when you run `gulp` from cli)
    gulp.task('base', ['minify-lib-scripts', 'minify-lib-css']);
})();