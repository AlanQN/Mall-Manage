'use strict';

var gulp = require('gulp');
var $ = require('gulp-load-plugins')();
var browserify = require('browserify');
var transform = require('vinyl-transform');
var markJSON = require('markit-json');
var docUtil = require('amazeui-doc-util');
var browserSync = require('browser-sync').create();
var del = require('del');
var runSequence = require('run-sequence');
var reload = browserSync.reload;

gulp.task('clean', function() {
  return del('dist');
});


gulp.task('copy', function() {
  return gulp.src('js/**/*.js')
    .pipe(gulp.dest('dist/js'));
});

gulp.task('docs', function(){
  return gulp.src(['README.md', 'docs/*.md'])
    .pipe(markJSON(docUtil.markedOptions))
    .pipe(docUtil.applyTemplate(null, {
      pluginTitle: 'Amaze UI Datetime Picker',
      pluginDesc: '使用 Amaze UI 样式风格的 jQuery Datetime Picker 插件。',
      buttons: 'amazeui/datetimepicker',
      head: '<link rel="stylesheet" href="../css/amazeui.datetimepicker.css"/>'
    }))
    .pipe($.rename(function(file) {
      file.basename = file.basename.toLowerCase();
      if (file.basename === 'readme') {
        file.basename = 'index';
      }
      file.extname = '.html';
    }))
    .pipe(gulp.dest(function(file) {
      if (file.relative === 'index.html') {
        return 'dist'
      }
      return 'dist/docs';
    }));
});

gulp.task('less', function() {
  return gulp.src('less/amazeui.datetimepicker.less')
    .pipe($.less())
    .pipe($.autoprefixer({browsers: docUtil.autoprefixerBrowsers}))
    .pipe($.csso())
    .pipe(gulp.dest('./dist/css'))
    .pipe(gulp.dest('./css'));
});

// Watch Files For Changes & Reload
gulp.task('dev', ['default'], function () {
  browserSync.init({
    notify: false,
    server: 'dist',
    logPrefix: 'AMP'
  });

  gulp.watch('dist/**/*', reload);
});

gulp.task('deploy', ['default'], function() {
  return gulp.src('dist/**/*')
    .pipe($.ghPages());
});

gulp.task('watch', function() {
  gulp.watch(['README.md', 'docs/*.md'], ['docs']);
  gulp.watch('less/*.less', ['less']);
});

gulp.task('default', function(cb) {
  runSequence('clean', ['copy', 'less', 'docs', 'watch'], cb);
});
