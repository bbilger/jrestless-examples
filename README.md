# JRestless Examples

[![Build Status](https://travis-ci.org/bbilger/jrestless-examples.svg?branch=master)](https://travis-ci.org/bbilger/jrestless-examples)
[![GitHub issues](https://img.shields.io/github/issues/bbilger/jrestless-examples.svg)](https://github.com/bbilger/jrestless-examples/issues)
[![GitHub license](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/bbilger/jrestless-examples/master/LICENSE)

This repository contains examples for [JRestlesss](https://github.com/bbilger/jrestless).

## Deployment

JRestless does not depend on the [serverless framework](https://github.com/serverless/serverless) but it simplifies the necessary AWS configuration tremendously. So all examples contain a `serverless` configuration and the installation descriptions assume you have `serverless` installed and configured.

You can install `serverless` as described in the docs https://serverless.com/framework/docs/guide/installing-serverless/

To run the AWS examples setup your AWS account as described in the docs https://serverless.com/framework/docs/providers/aws/guide/credentials/

## Build

All examples can be built either with Gradle or Maven. The default build system, however, is Gradle.

If you want to use Maven you have to replace `artifact: build/distributions/SOME-EXAMPLE.zip` by `artifact: target/SOME-EXAMPLE.jar` in all `serverless.yml` files or at least the example you want to try out. You can run the following script to do this automatically:

```bash
git clone https://github.com/bbilger/jrestless-examples.git
cd jrestless-examples
find . -path ./.git -prune -o -name 'serverless.yml' -type f -exec sed -i 's/artifact: build\/distributions\/\([a-z0-9-]\+\)\.zip/artifact: target\/\1.jar/' {} +
```

The descriptions of the examples are also valid for Gradle, only. If you use Maven, use "mvn package" instead of "./gradlew build".

## Examples

* [AWS](aws)
  * [API Gateway](aws/gateway)
    * [aws-gateway-showcase](aws/gateway/aws-gateway-showcase)
      * Example showing JRestless' features.
    * [aws-gateway-spring](aws/gateway/aws-gateway-spring)
      * Example showing how to use Spring in JRestless.
    * [aws-gateway-cdi](aws/gateway/aws-gateway-cdi)
      * Example showing how to use CDI/Weld in JRestless.
    * [aws-gateway-guice](aws/gateway/aws-gateway-guice)
      * Example showing how to use Guice in JRestless.
    * [aws-gateway-usage-example](aws/gateway/aws-gateway-usage-example)
      * Simple JRestless usage example.
    * [aws-gateway-binary](aws/gateway/aws-gateway-binary)
      * Example showing how to return and receive binary data.
    * [aws-gateway-security-cognito-authorizer](aws/gateway/aws-gateway-security-cognito-authorizer)
      * Example showing how to use a cognito user pool authorizer.
    * [aws-gateway-security-custom-authorizer](aws/gateway/aws-gateway-security-custom-authorizer)
      * Example showing how to use a custom authorizer.
  * [Lambda Service Function](aws/service)
    * [aws-service-usage-example](aws/service/aws-service-usage-example)
      * Example showing how to invoke one Lambda (service) function from another (API Gateway).
  * [SNS Function](aws/sns)
    * [aws-sns-usage-example](aws/sns/aws-sns-usage-example)
      * Example showing how to use JRestless to handle SNS notifications.
